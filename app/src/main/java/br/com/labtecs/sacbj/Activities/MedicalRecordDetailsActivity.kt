package br.com.labtecs.sacbj.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.labtecs.sacbj.R
import kotlinx.android.synthetic.main.activity_medical_record_details.*

class MedicalRecordDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record_details)

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        private val EXTRA_MEDICAL_RECORD_ID = "medical_record_id"

        fun open(context: Context?, id:Long){
            context?.startActivity(Intent(context, MedicalRecordDetailsActivity::class.java))
        }
    }
}
