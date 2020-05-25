package br.com.labtecs.sacbj.Interfaces

import br.com.labtecs.sacbj.Models.Patient

interface FindPatientInterface {
    fun findPatient(patients: List<Patient>)
}