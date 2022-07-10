package com.tema.gunshop.system.di

import android.app.Application
import androidx.room.Room
import com.tema.gunshop.program.database.GunShopDatabase
import com.tema.gunshop.program.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideGamesShopDatabase(
        application: Application,
    ): GunShopDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            GunShopDatabase::class.java,
            "games-shop",
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(
        gunShopDatabase: GunShopDatabase,
    ): UserDao {
        return gunShopDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideDeveloperDao(
        gunShopDatabase: GunShopDatabase,
    ): DeveloperDao {
        return gunShopDatabase.developerDao()
    }

    @Singleton
    @Provides
    fun provideGameDao(
        gunShopDatabase: GunShopDatabase,
    ): GunDao {
        return gunShopDatabase.gunDao()
    }

    @Singleton
    @Provides
    fun provideChequeDao(
        gunShopDatabase: GunShopDatabase,
    ): ChequeDao {
        return gunShopDatabase.chequeDao()
    }

    @Singleton
    @Provides
    fun providePlatformDao(
        gunShopDatabase: GunShopDatabase,
    ): PlatformDao {
        return gunShopDatabase.platformDao()
    }
}