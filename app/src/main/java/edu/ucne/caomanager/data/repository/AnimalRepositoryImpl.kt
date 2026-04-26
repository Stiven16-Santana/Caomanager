package edu.ucne.caomanager.data.repository


import edu.ucne.caomanager.CaomanagerApp
import edu.ucne.caomanager.data.local.entity.dao.AnimalDao
import edu.ucne.caomanager.data.local.entity.mapper.toDomain
import edu.ucne.caomanager.data.local.entity.mapper.toEntity
import edu.ucne.caomanager.domain.model.Animal
import edu.ucne.caomanager.domain.model.repository.AnimalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val dao: AnimalDao
) : AnimalRepository {

    override suspend fun insert(animal: Animal) {
        dao.insert(animal.toEntity())
    }

    override suspend fun delete(animal: Animal) {
        dao.delete(animal.toEntity())
    }

    override fun getAnimales(): Flow<List<Animal>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }
}

