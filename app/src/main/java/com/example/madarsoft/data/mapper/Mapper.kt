package com.example.madarsoft.data.mapper

import com.example.madarsoft.data.source.local.UserEntity
import com.example.madarsoft.domain.enitiy.User

/**
 * Created by Aziza Helmy on 27/06/2025.
 */


fun User.toEntity() =
    UserEntity(id = id, name = name, age = age, title = title, job = job, gender = gender)

fun UserEntity.toUser() =
    User(id = id, name = name, age = age, title = title, job = job, gender = gender)
