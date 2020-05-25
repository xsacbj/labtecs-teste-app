package br.com.labtecs.sacbj.Interfaces

import br.com.labtecs.sacbj.Models.MedicalRecord

interface MedicalRecordRepositoryInterface {
    fun save(medicalRecord: MedicalRecord)
    fun findById(id: Long, callback: (MedicalRecord?) -> Unit)
    fun search(query: String, callback: (List<MedicalRecord>) -> Unit)
}