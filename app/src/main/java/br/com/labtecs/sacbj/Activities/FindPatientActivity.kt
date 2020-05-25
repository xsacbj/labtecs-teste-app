package br.com.labtecs.sacbj.Activities

import android.content.Intent
import android.graphics.MaskFilter
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import br.com.labtecs.sacbj.Interfaces.FindPatientInterface
import br.com.labtecs.sacbj.Models.Patient
import br.com.labtecs.sacbj.Objetcs.PatientRepository
import br.com.labtecs.sacbj.Presenters.FindPatientPresenter
import br.com.labtecs.sacbj.R
import kotlinx.android.synthetic.main.activity_find_patient.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class FindPatientActivity : AppCompatActivity(), FindPatientInterface{

    private val presenter = FindPatientPresenter(this, PatientRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_patient)

        edtCPF.addTextChangedListener(Mask.mask("###.###.###-##",edtCPF))

        btnNext.setOnClickListener {
            val cpf = edtCPF.text.toString()
            if (isValidCPF(cpf)){
                presenter.findPatient(cpf.replace(".", "").replace("-", ""))
            }
        }
    }

    override fun findPatient(patients: List<Patient>) {
        if (patients.isNotEmpty()){
            var patient = patients[0]
            MedicalRecordFormActivity.open(this, patient.id)
        }else{
            val url = "https://api.cpfcnpj.com.br/5ae973d7a997af13f0aaf2bf60e65803/1/"+edtCPF.text.toString().replace(".", "").replace("-", "")
            AsyncTaskHandleJson().execute(url)
        }
    }

    fun isValidCPF(cpf:String):Boolean{
        val cpfClean = cpf.replace(".", "").replace("-", "")

        if (cpfClean.length != 11)
            return false

        try {
            val number  = cpfClean.toLong()
        }catch (e : Exception){
            return false
        }

        var dvCurrent10 = cpfClean.substring(9,10).toInt()
        var dvCurrent11= cpfClean.substring(10,11).toInt()

        val cpfNineFirst = IntArray(9)
        var i = 9
        while (i > 0 ) {
            cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
            i--
        }

        var sumProductNine = IntArray(9)
        var weight = 10
        var position = 0

        while (weight >= 2){
            sumProductNine[position] = weight * cpfNineFirst[position]
            weight--
            position++
        }

        var dvForTenthDigit = sumProductNine.sum() % 11
        dvForTenthDigit = 11 - dvForTenthDigit

        if(dvForTenthDigit > 9) { dvForTenthDigit = 0 }
        if (dvForTenthDigit != dvCurrent10){ return false }


        var cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10

        var sumProductTen = IntArray(10)
        var w = 11
        var p = 0
        while (w >= 2){
            sumProductTen[p] = w * cpfTenFirst[p]
            w--
            p++
        }

        var dvForeleventhDigit = sumProductTen.sum() % 11
        dvForeleventhDigit = 11 - dvForeleventhDigit

        if(dvForeleventhDigit > 9){ dvForeleventhDigit = 0 }
        if (dvForeleventhDigit != dvCurrent11){ return false }

        return true
    }


    class Mask{
        companion object {
            private fun replaceChars(cpfFull : String) : String{
                return cpfFull.replace(".", "").replace("-", "")
                    .replace("(", "").replace(")", "")
                    .replace("/", "").replace(" ", "")
                    .replace("*", "")
            }


            fun mask(mask : String, etCpf : EditText) : TextWatcher{

                val textWatcher : TextWatcher = object : TextWatcher {
                    var isUpdating : Boolean = false
                    var oldString : String = ""
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                    }

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        val str = replaceChars(s.toString())
                        var cpfWithMask = ""

                        if (count == 0)//is deleting
                            isUpdating = true

                        if (isUpdating){
                            oldString = str
                            isUpdating = false
                            return
                        }

                        var i = 0
                        for (m : Char in mask.toCharArray()){
                            if (m != '#' && str.length > oldString.length){
                                cpfWithMask += m
                                continue
                            }
                            try {
                                cpfWithMask += str.get(i)
                            }catch (e : Exception){
                                break
                            }
                            i++
                        }

                        isUpdating = true
                        etCpf.setText(cpfWithMask)
                        etCpf.setSelection(cpfWithMask.length)

                    }

                    override fun afterTextChanged(editable: Editable) {

                    }
                }

                return textWatcher
            }
        }
    }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>(){
        override fun doInBackground(vararg url: String?): String {
            var text: String
            val conection = URL(url[0]).openConnection() as HttpURLConnection

            try {
                conection.connect()
                text = conection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                conection.disconnect()
            }

            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    private fun handleJson(jsonString: String?) {
        val jsonObject = JSONObject(jsonString)

        var patient = Patient(0, jsonObject.getString("nome"), jsonObject.getString("cpf").replace(".", "").replace("-", ""), null)
        presenter.savePatient(patient)
        presenter.findPatient(patient.cpf)
    }

}
