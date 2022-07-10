package com.tema.gunshop.program.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "gun",
    foreignKeys = [ForeignKey(
        entity = DeveloperEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("developer_id"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class GunEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "developer_id") val developerId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "min_age") val minAge: Int,
    @ColumnInfo(name = "rating") val rating: Int,
)


