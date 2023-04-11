package com.petiveriaalliacea.taza.mappers;

import com.petiveriaalliacea.taza.dto.SpecialOfferDto;
import com.petiveriaalliacea.taza.entities.SpecialOffer;
import org.mapstruct.InjectionStrategy;

@org.mapstruct.Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface SpecialOfferMapper {
    SpecialOfferDto toSpecialOfferDto(SpecialOffer specialOffer);
    SpecialOffer toSpecialOffer(SpecialOfferDto specialOfferDto);

}
