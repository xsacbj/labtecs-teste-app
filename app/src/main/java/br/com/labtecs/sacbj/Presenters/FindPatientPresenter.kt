package br.com.labtecs.sacbj.Presenters

import br.com.labtecs.sacbj.Interfaces.FindPatientInterface
import br.com.labtecs.sacbj.Models.Patient
import br.com.labtecs.sacbj.Objetcs.PatientRepository

class FindPatientPresenter(
    private val view:FindPatientInterface,
    private val repository: PatientRepository
){
    fun findPatient(query: String){
        repository.search(query){patients ->
            view.findPatient(patients)
        }
    }

    fun savePatient(patient: Patient) {
        repository.save(patient)
    }
}