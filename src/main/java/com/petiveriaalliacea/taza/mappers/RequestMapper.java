package com.petiveriaalliacea.taza.mappers;

import com.petiveriaalliacea.taza.dto.RequestDto;
import com.petiveriaalliacea.taza.entities.Request;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper( RequestMapper.class );
    RequestDto toRequestDto(Request request);
    Request toRequest(RequestDto requestDto);
}
