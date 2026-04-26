package edu.ucne.caomanager.domain.model

data class Persona(
    val id: Int = 0,
    val nombre: String,
    val tipo: String,
    val telefono: String? = null,
    val direccion: String? = null
)