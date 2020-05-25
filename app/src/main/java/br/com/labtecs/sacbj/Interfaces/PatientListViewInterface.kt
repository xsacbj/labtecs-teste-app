package br.com.labtecs.sacbj.Interfaces

import br.com.labtecs.sacbj.Models.Patient

interface PatientListViewInterface {
    fun showPatients(patients: List<Patient>)
    fun showPatientDetails(patient: Patient)
}