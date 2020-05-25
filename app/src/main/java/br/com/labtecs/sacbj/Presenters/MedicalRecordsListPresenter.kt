package br.com.labtecs.sacbj.Presenters

import br.com.labtecs.sacbj.Interfaces.MedicalRecordListViewInterface
import br.com.labtecs.sacbj.Models.MedicalRecord
import br.com.labtecs.sacbj.Objetcs.MedicalRecordRepository

class MedicalRecordsListPresenter(
    private val view: MedicalRecordListViewInterface,
    private val repository: MedicalRecordRepository
){
    fun searchMedicalRecord(query: String){
        repository.search(query){ medicalRecords ->
            view.showMedicalRecords(medicalRecords)
        }
    }

    fun showMedicalRecordDetails(medicalRecord: MedicalRecord){
        view.showMedicalRecordDetails(medicalRecord)
    }
}