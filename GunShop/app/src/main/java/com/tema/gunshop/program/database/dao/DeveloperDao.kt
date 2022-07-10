package com.tema.gunshop.program.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tema.gunshop.program.database.entity.DeveloperEntity

@Dao
interface DeveloperDao {

    @Insert
    fun insertAll(vararg developers: DeveloperEntity)

    @Delete
    fun delete(developer: DeveloperEntity)

    @Query("SELECT * FROM developer")
    fun getAll(): List<DeveloperEntity>

    @Query("SELECT name FROM developer WHERE id = :developerId")
    fun getDeveloperNameById(developerId: Int): String

    @Query("SELECT * FROM developer where login = :login AND password = :password")
    fun getDeveloperByLoginAndPassword(login: String, password: String): DeveloperEntity?

    @Query("SELECT * FROM developer where login = :login")
    fun getDeveloperByLogin(login: String): DeveloperEntity?
}