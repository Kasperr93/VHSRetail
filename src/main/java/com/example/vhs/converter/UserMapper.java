package com.example.vhs.converter;

import com.example.vhs.dto.UserData;
import com.example.vhs.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserData userEntityToUserDto(UserEntity userEntity);

    UserEntity userDtoToUserEntity(UserData userData);
}