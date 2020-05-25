package br.com.labtecs.sacbj.Interfaces

import br.com.labtecs.sacbj.Models.MedicalRecord

interface MedicalRecordListViewInterface {
    fun showMedicalRecords(medicalrecords: List<MedicalRecord>)
    fun showMedicalRecordDetails(medicalRecord: MedicalRecord)
}