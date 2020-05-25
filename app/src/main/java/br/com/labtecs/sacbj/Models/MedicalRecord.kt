package br.com.labtecs.sacbj.Models

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class MedicalRecord(
    var id: Long = 0,
    var pain: Boolean = false,
    var pain_intensity: Int? = 0,
    var disconfort: Boolean = false,
    var disconfort_body_paty: Int? = 0,
    var surgery: Boolean = false,
    var surgery_how_long: Int? = 0,
    var surgery_type: Int? = 0,
    var surgery_pain_after: Boolean? = false,
    var patient_id: Long,
    var create_at: Date?
) {
    override fun toString(): String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(create_at)
    }
}