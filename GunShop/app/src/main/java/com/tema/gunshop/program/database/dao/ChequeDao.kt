package com.tema.gunshop.program.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tema.gunshop.program.database.entity.ChequeEntity

@Dao
interface ChequeDao {

    @Insert
    fun insertAll(vararg cheques: ChequeEntity)

    @Delete
    fun delete(cheque: ChequeEntity)

    @Query("SELECT * FROM cheque")
    fun getAll(): List<ChequeEntity>

    @Query("SELECT gun_id FROM cheque WHERE user_id = :userId")
    fun getGameIdsByUserId(userId: Int): List<Int>
}