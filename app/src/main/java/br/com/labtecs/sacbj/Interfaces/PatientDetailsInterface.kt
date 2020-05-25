package br.com.labtecs.sacbj.Interfaces

import br.com.labtecs.sacbj.Models.Patient

interface PatientDetailsInterface {
    fun findById(patient: Patient?)
}