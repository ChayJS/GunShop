package com.tema.gunshop.program.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "cheque",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = CASCADE,
        ),
        ForeignKey(
            entity = GunEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("gun_id"),
            onDelete = CASCADE,
        ),
    ]
)
data class ChequeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "gun_id") val gunId: Int,
)