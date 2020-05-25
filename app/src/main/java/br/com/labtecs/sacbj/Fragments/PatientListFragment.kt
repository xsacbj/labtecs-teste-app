package br.com.labtecs.sacbj.Fragments

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import br.com.labtecs.sacbj.Interfaces.PatientListViewInterface
import br.com.labtecs.sacbj.Models.Patient
import br.com.labtecs.sacbj.Objetcs.PatientRepository
import br.com.labtecs.sacbj.Presenters.PatientsListPresenter

class PatientListFragment: ListFragment(), PatientListViewInterface{
    private val presenter = PatientsListPresenter(this, PatientRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchPatient("")
    }

    override fun showPatientDetails(patient: Patient) {
        if (activity is OnPatientClickListener){
            val listener = activity as OnPatientClickListener
            listener.onPatientClick(patient)
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        var patient = l.getItemAtPosition(position) as Patient
        presenter.showPatientDetails(patient)
    }

    override fun showPatients(patients: List<Patient>) {
        val adapter = ArrayAdapter<Patient>(requireContext(), android.R.layout.simple_list_item_1, patients)
        listAdapter = adapter
    }

    interface OnPatientClickListener{
        fun onPatientClick(patient: Patient)
    }

}