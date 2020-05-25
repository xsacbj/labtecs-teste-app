package br.com.labtecs.sacbj.Presenters

import br.com.labtecs.sacbj.Interfaces.PatientListViewInterface
import br.com.labtecs.sacbj.Models.Patient
import br.com.labtecs.sacbj.Objetcs.PatientRepository

class PatientsListPresenter(
    private val view: PatientListViewInterface,
    private val repository: PatientRepository
){
    fun searchPatient(query: String){
        repository.search(query) { patients ->
            view.showPatients(patients)
        }
    }

    fun showPatientDetails(patient: Patient){
        view.showPatientDetails(patient)
    }
}