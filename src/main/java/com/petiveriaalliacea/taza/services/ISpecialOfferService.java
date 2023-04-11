package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.SpecialOfferDto;

import java.util.List;

public interface ISpecialOfferService {
    SpecialOfferDto getSpecialOfferById(Long id);
    List<SpecialOfferDto> getAllSpecialOffers();
    SpecialOfferDto saveSpecialOffer(SpecialOfferDto specialOfferDto);
    SpecialOfferDto updateSpecialOffer(Long id, SpecialOfferDto specialOfferDto);
    String deleteSpecialOffer(Long id);

}
