package edu.ucne.caomanager.domain.model

data class Animal(
    val id: Int = 0,
    val codigo: String,
    val fechaNacimiento: String? = null,
    val pesoInicial: Double,
    val precioCompra: Double,
    val estado: String,
    val pesoActual: Double
)