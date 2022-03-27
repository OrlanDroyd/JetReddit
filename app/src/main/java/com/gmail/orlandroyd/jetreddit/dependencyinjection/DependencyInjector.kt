package com.gmail.orlandroyd.jetreddit.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.gmail.orlandroyd.jetreddit.data.database.dbmapper.DbMapper
import com.gmail.orlandroyd.jetreddit.data.database.dbmapper.DbMapperImpl
import com.gmail.orlandroyd.jetreddit.data.repository.Repository
import com.gmail.orlandroyd.jetreddit.data.repository.RepositoryImpl
import com.gmail.orlandroyd.jetreddit.data.database.AppDatabase
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * Provides dependencies across the app.
 */
@DelicateCoroutinesApi
class DependencyInjector(applicationContext: Context) {

    val repository: Repository by lazy { provideRepository(database) }

    private val database: AppDatabase by lazy { provideDatabase(applicationContext) }
    private val dbMapper: DbMapper = DbMapperImpl()

    private fun provideDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    private fun provideRepository(database: AppDatabase): Repository {
        val postDao = database.postDao()

        return RepositoryImpl(postDao, dbMapper)
    }
}