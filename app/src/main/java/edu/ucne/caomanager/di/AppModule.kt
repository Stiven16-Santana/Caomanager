package edu.ucne.caomanager.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.caomanager.data.local.entity.dao.AnimalDao
import edu.ucne.caomanager.data.local.database.CaoManagerDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 1. Enseñamos a Hilt a crear la Base de Datos
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CaoManagerDb {
        return Room.databaseBuilder(
            context,
            CaoManagerDb::class.java,
            "CaoManager.db"
        ).fallbackToDestructiveMigration().build()
    }

    // 2. Enseñamos a Hilt a obtener el DAO desde la base de datos
    @Provides
    @Singleton
    fun provideAnimalDao(db: CaoManagerDb): AnimalDao {
        return db.animalDao()
    }

}