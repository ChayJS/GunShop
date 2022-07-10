package com.tema.gunshop.program.database.dao

import androidx.room.*
import com.tema.gunshop.program.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg users: UserEntity)

    @Update
    fun update(vararg users: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM user where login = :login AND password = :password")
    fun getUserByLoginAndPassword(login: String, password: String): UserEntity?

    @Query("SELECT * FROM user where login = :login")
    fun getUserByLogin(login: String): UserEntity?

    @Query("SELECT * FROM user where id = :userId")
    fun getUserById(userId: Int): UserEntity?

    @Query("SELECT user_id FROM cheque GROUP BY user_id ORDER BY COUNT(user_id) DESC LIMIT :count")
    fun getUsersIdsByGamesCount(count: Int): List<Int>
}