package edu.ucne.caomanager.domain.model

data class DetalleCompra(
    val id: Int = 0,
    val idCompra: Int,
    val idAnimal: Int,
    val precioUnitario: Double
)