package com.shopping.study.user.mapper

import com.shopping.study.user.dto.UserDto
import com.shopping.study.user.entity.UserEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDto(userEntity: UserEntity): UserDto
}
