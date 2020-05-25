package br.com.labtecs.sacbj.Objetcs

import br.com.labtecs.sacbj.Interfaces.PatientRepositoryInterface
import br.com.labtecs.sacbj.Models.Patient

object PatientRepository : PatientRepositoryInterface {

    private var nextId = 1L
    private var patientList = mutableListOf<Patient>()

    init {
        save(Patient(
            0,
            "José Bezerra",
            "003124583491",
            null
        ))
    }

    override fun save(patient: Patient) {
        if (patient.id == 0L){
            patient.id = nextId++
            patientList.add(patient)
        } else {
            val index = patientList.indexOfFirst { it.id == patient.id }
            if (index > -1){
                patientList[index] = patient
            } else {
                patientList.add(patient)
            }
        }
    }


    override fun findById(id: Long, callback: (Patient?) -> Unit) {
        callback(patientList.find { it.id == id })
    }

    override fun search(query: String, callback: (List<Patient>) -> Unit) {
        callback(
            if (query.isEmpty()) patientList
            else patientList.filter{
                (it.name.toUpperCase().contains(query.toUpperCase())) or ( it.cpf == query)
            }
        )
    }
}