package com.tema.gunshop.program.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tema.gunshop.program.database.dao.*
import com.tema.gunshop.program.database.entity.*

@Database(
    version = 1,
    entities = [
        UserEntity::class,
        DeveloperEntity::class,
        GunEntity::class,
        PlatformEntity::class,
        ChequeEntity::class,
    ],
)
abstract class GunShopDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun developerDao(): DeveloperDao

    abstract fun gunDao(): GunDao

    abstract fun chequeDao(): ChequeDao

    abstract fun platformDao(): PlatformDao
}