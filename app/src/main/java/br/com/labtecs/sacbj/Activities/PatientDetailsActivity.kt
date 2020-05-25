package br.com.labtecs.sacbj.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.labtecs.sacbj.Fragments.MedicalRecordListFragment
import br.com.labtecs.sacbj.Interfaces.PatientDetailsInterface
import br.com.labtecs.sacbj.Models.Patient
import br.com.labtecs.sacbj.Objetcs.PatientRepository
import br.com.labtecs.sacbj.Presenters.PatientDetailsPresenter
import br.com.labtecs.sacbj.Presenters.PatientsListPresenter
import br.com.labtecs.sacbj.R
import kotlinx.android.synthetic.main.activity_patient_details.*

class PatientDetailsActivity : AppCompatActivity(), PatientDetailsInterface{

    private val patientId: Long by lazy { intent.getLongExtra(EXTRA_PATIENT_ID, -1)}
    private val listFragment: MedicalRecordListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as MedicalRecordListFragment
    }
    private val presenter = PatientDetailsPresenter(this, PatientRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        presenter.findById(patientId)

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun findById(patient: Patient?) {
        if (patient != null){
            txvName.text = patient.name
            txvCPF.text = patient.cpf
        }
    }

    fun getId(): Long {
        return patientId
    }

    companion object {
        private const val EXTRA_PATIENT_ID = "patient_id"

        fun open(context: Context, id: Long){
            context.startActivity(Intent(context, PatientDetailsActivity::class.java).apply {
                putExtra(EXTRA_PATIENT_ID, id)
            })
        }

    }
}
