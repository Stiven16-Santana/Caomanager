package edu.ucne.caomanager.domain.model.repository


import edu.ucne.caomanager.domain.model.Animal
import kotlinx.coroutines.flow.Flow

interface AnimalRepository {
    suspend fun insert(animal: Animal)
    suspend fun delete(animal: Animal)
    fun getAnimales(): Flow<List<Animal>>
}