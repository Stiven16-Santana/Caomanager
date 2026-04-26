package edu.ucne.caomanager.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.caomanager.data.local.entity.AnimalEntity
import edu.ucne.caomanager.data.local.entity.dao.AnimalDao


@Database(
    entities = [AnimalEntity::class], // Aquí pones todas tus entidades (tablas)
    version = 1,
    exportSchema = false
)
abstract class CaoManagerDb : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}