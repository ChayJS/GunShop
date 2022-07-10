package com.tema.gunshop.system.di

import com.tema.gunshop.system.entity.mappers.GameEntityToGameMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Provides
    @Singleton
    fun provideGameEntityToGameMapper() = GameEntityToGameMapper()
}