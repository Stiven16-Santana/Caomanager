package edu.ucne.caomanager.domain.model

data class Gasto(
    val id: Int = 0,
    val fecha: String,
    val tipo: String,
    val monto: Double,
    val idAnimal: Int? = null
)