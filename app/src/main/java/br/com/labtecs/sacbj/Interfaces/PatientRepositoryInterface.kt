package br.com.labtecs.sacbj.Interfaces

import br.com.labtecs.sacbj.Models.Patient

interface PatientRepositoryInterface{
    fun save(patient: Patient)
    fun findById(id: Long, callback: (Patient?) -> Unit)
    fun search(query: String, callback: (List<Patient>) -> Unit)
}