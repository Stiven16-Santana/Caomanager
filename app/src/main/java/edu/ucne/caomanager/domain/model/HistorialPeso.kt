package edu.ucne.caomanager.domain.model

data class HistorialPeso(
    val id: Int = 0,
    val idAnimal: Int,
    val fecha: String,
    val peso: Double
)