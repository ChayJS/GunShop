package com.tema.gunshop.system.entity.enums

enum class Role {
    User, Developer, Admin;

    override fun toString() = when (this) {
        User -> "Клієнт"
        Developer -> "Менеджер"
        Admin -> "Адміністратор"
    }
}