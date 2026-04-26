package edu.ucne.caomanager.domain.model

data class Compra(
    val id: Int = 0,
    val fecha: String,
    val idProveedor: Int,
    val total: Double,
    val observacion: String? = null
)