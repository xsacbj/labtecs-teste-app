package br.com.labtecs.sacbj.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.labtecs.sacbj.Fragments.PatientListFragment
import br.com.labtecs.sacbj.Models.Patient
import br.com.labtecs.sacbj.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    PatientListFragment.OnPatientClickListener
{

    private val listFragment: PatientListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as PatientListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNewPatient.setOnClickListener {
            startActivity(Intent(this, FindPatientActivity::class.java))
        }
    }

    override fun onPatientClick(patient: Patient) {
        showPatientDetailsActivity(patient.id)
    }

    private fun showPatientDetailsActivity(id: Long) {
        PatientDetailsActivity.open(this, id)
    }

}
