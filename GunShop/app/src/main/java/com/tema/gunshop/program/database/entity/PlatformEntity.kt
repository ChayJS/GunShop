package com.tema.gunshop.program.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "platform",
    foreignKeys = [ForeignKey(
        entity = GunEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("gun_id"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class PlatformEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "gun_id") val gunId: Int,
    @ColumnInfo(name = "name") val name: String,
)