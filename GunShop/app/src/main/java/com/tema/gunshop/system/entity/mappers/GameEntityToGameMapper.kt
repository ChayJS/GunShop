package com.tema.gunshop.system.entity.mappers

import com.tema.gunshop.program.database.entity.GunEntity
import com.tema.gunshop.system.entity.dataclasses.Game

class GameEntityToGameMapper {

    fun map(gunEntity: GunEntity, developerName: String): Game {
        return Game(
            id = gunEntity.id,
            developerId = gunEntity.developerId,
            developerName = developerName,
            name = gunEntity.name,
            genre = gunEntity.genre,
            price = gunEntity.price,
            minAge = gunEntity.minAge,
            rating = gunEntity.rating,
        )
    }
}