package edu.ucne.caomanager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animales")
data class AnimalEntity(
    @PrimaryKey(autoGenerate = true)
    val idAnimal: Int = 0,
    val codigo: String,
    val fechaNacimiento: String?,
    val pesoInicial: Double,
    val precioCompra: Double,
    val estado: String,
    val observacion: String = ""
)