package edu.ucne.caomanager.domain.model

data class DetalleVenta(
    val id: Int = 0,
    val idVenta: Int,
    val idAnimal: Int,
    val precioVenta: Double,
    val pesoVenta: Double
)