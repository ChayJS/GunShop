package com.tema.gunshop.program.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tema.gunshop.program.database.entity.GunEntity

@Dao
interface GunDao {

    @Insert
    fun insertAll(vararg games: GunEntity)

    @Delete
    fun delete(game: GunEntity?)

    @Query("SELECT * FROM gun")
    fun getAll(): List<GunEntity>

    @Query("SELECT * FROM gun WHERE id = :gameId")
    fun getGameById(gameId: Int): GunEntity?

    @Query("SELECT * FROM gun WHERE developer_id = :developerId")
    fun getGamesByDeveloperId(developerId: Int): List<GunEntity>

    @Query("SELECT gun_id FROM cheque GROUP BY gun_id ORDER BY COUNT(gun_id) DESC LIMIT :count")
    fun getTheMostPopularGamesIds(count: Int): List<Int>

    @Query("SELECT * FROM gun ORDER BY price DESC LIMIT :count")
    fun getTheMostExpensiveGames(count: Int): List<GunEntity>
}