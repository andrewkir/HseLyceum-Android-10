package ru.andrewkir.saturday10.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.andrewkir.saturday10.db.GoodsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): GoodsDatabase {
        return Room.databaseBuilder(
            context,
            GoodsDatabase::class.java,
            "goods-db"
        )
            .allowMainThreadQueries()
            .build()
    }
}