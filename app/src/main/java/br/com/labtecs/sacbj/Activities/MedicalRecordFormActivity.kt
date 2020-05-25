package br.com.labtecs.sacbj.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.labtecs.sacbj.R
import kotlinx.android.synthetic.main.activity_medical_record_form.*

class MedicalRecordFormActivity : AppCompatActivity() {

    private val patientId: Long by lazy { intent.getLongExtra(MedicalRecordFormActivity.EXTRA_PATIENT_ID, -1)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record_form)

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    companion object {
        private val EXTRA_PATIENT_ID = "patient_id"
        fun open(context: Context, id: Long){
            context.startActivity(Intent(context, MedicalRecordFormActivity::class.java).apply {
                putExtra(EXTRA_PATIENT_ID, id)
            })
        }
    }
}
