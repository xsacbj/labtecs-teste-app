package br.com.labtecs.sacbj.Models

import java.util.*


data class Patient(
    var id: Long = 0,
    var name: String = "",
    var cpf: String = "",
    var last_service: Date?
) {
    override fun toString(): String {
        return name
    }
}