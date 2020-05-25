package br.com.labtecs.sacbj.Presenters

import br.com.labtecs.sacbj.Interfaces.PatientDetailsInterface
import br.com.labtecs.sacbj.Objetcs.PatientRepository

class PatientDetailsPresenter(
    private val view:PatientDetailsInterface,
    private val repository: PatientRepository
){
    fun findById(id: Long){
        repository.findById(id){patient ->
            view.findById(patient)
        }
    }
}