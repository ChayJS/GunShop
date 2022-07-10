package com.tema.gunshop.program.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tema.gunshop.program.database.entity.PlatformEntity

@Dao
interface PlatformDao {

    @Insert
    fun insertAll(vararg platforms: PlatformEntity)

    @Delete
    fun delete(platform: PlatformEntity)

    @Query("SELECT * FROM platform")
    fun getAll(): List<PlatformEntity>

    @Query("SELECT * FROM platform WHERE id = :platformId")
    fun getPlatformById(platformId: Int): List<PlatformEntity>

    @Query("SELECT * FROM platform GROUP BY gun_id ORDER BY COUNT(gun_id) DESC LIMIT :count")
    fun getTheMostPopularPlatformsIds(count: Int): List<PlatformEntity>
}