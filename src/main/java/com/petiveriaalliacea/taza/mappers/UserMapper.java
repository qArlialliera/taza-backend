package com.petiveriaalliacea.taza.mappers;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser (UserRequestDto userDto);
}
