package edu.ucne.caomanager.domain.model

data class Venta(
    val id: Int = 0,
    val fecha: String,
    val idCliente: Int,
    val total: Double,
    val observacion: String? = null
)