package br.com.labtecs.sacbj.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import br.com.labtecs.sacbj.Activities.MedicalRecordDetailsActivity
import br.com.labtecs.sacbj.Activities.PatientDetailsActivity
import br.com.labtecs.sacbj.Interfaces.MedicalRecordListViewInterface
import br.com.labtecs.sacbj.Models.MedicalRecord
import br.com.labtecs.sacbj.Objetcs.MedicalRecordRepository
import br.com.labtecs.sacbj.Presenters.MedicalRecordsListPresenter

class MedicalRecordListFragment : ListFragment(), MedicalRecordListViewInterface {

    private val presenter = MedicalRecordsListPresenter(this, MedicalRecordRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchMedicalRecord((activity as PatientDetailsActivity).getId().toString())
    }

    override fun showMedicalRecordDetails(medicalRecord: MedicalRecord) {
        MedicalRecordDetailsActivity.open(context, medicalRecord.id)
    }

    override fun showMedicalRecords(medicalrecords: List<MedicalRecord>) {
        val adapter = ArrayAdapter<MedicalRecord>(requireContext(), android.R.layout.simple_list_item_1, medicalrecords)
        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        var medicalRecord = l.getItemAtPosition(position) as MedicalRecord
        presenter.showMedicalRecordDetails(medicalRecord)
    }


}
