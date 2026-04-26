package edu.ucne.caomanager.data.local.entity.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.caomanager.data.local.entity.AnimalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: AnimalEntity)

    @Query("SELECT * FROM animales")
    fun getAll(): Flow<List<AnimalEntity>>

    @Delete
    suspend fun delete(animal: AnimalEntity)
}