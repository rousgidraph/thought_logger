package com.example.log_thoughts.di

import android.content.Context
import androidx.room.Room
import com.example.log_thoughts.data.AppDatabase
import com.example.log_thoughts.data.ThoughtDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {


    @Provides
    fun providethoughtDao(database : AppDatabase): ThoughtDao{
        return database.ThoughtDao()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return  Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "thoughts.db"
        ).build()
    }

}