package br.com.labtecs.sacbj.Objetcs

import br.com.labtecs.sacbj.Interfaces.MedicalRecordRepositoryInterface
import br.com.labtecs.sacbj.Models.MedicalRecord
import java.util.*



object MedicalRecordRepository : MedicalRecordRepositoryInterface {
    private var nextId = 1L
    private var medicalRecordList = mutableListOf<MedicalRecord>()

    init {
        save(MedicalRecord(
            0,
            true,
            6,
            true,
            1,
            false,
            null,
            null,
            null,
            1,
            null

        ))
    }

    override fun save(medicalRecord: MedicalRecord) {

        medicalRecord.create_at = Calendar.getInstance().time


        if (medicalRecord.id == 0L){
            medicalRecord.id = nextId++
            medicalRecordList.add(medicalRecord)
        } else {
            val index = medicalRecordList.indexOfFirst { it.id == medicalRecord.id }
            if (index > -1){
                medicalRecordList[index] = medicalRecord
            } else {
                medicalRecordList.add(medicalRecord)
            }
        }
    }

    override fun findById(id: Long, callback: (MedicalRecord?) -> Unit) {
        callback(medicalRecordList.find { it.id == id })
    }

    override fun search(query: String, callback: (List<MedicalRecord>) -> Unit) {
        callback(
            if (query.isEmpty()) medicalRecordList
            else medicalRecordList.filter{
                it.patient_id == query.toLong()
            }
        )
    }
}