package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.SpecialOfferDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.SpecialOffer;
import com.petiveriaalliacea.taza.mappers.SpecialOfferMapper;
import com.petiveriaalliacea.taza.repositories.SpecialOfferRepository;
import com.petiveriaalliacea.taza.services.ISpecialOfferService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SpecialOfferService implements ISpecialOfferService {
    private final SpecialOfferRepository specialOfferRepository;
    private final SpecialOfferMapper specialOfferMapper;

    @Override
    public SpecialOfferDto getSpecialOfferById(Long id) {
        SpecialOffer specialOffer = specialOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Special offer with id " + id + " not found"));
        return specialOfferMapper.toSpecialOfferDto(specialOffer);
    }

    @Override
    public List<SpecialOfferDto> getAllSpecialOffers() {
        List<SpecialOffer> specialOffers = specialOfferRepository.findAll();
        return specialOffers.stream()
                .map(specialOfferMapper::toSpecialOfferDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialOfferDto saveSpecialOffer(SpecialOfferDto specialOfferDto) {
        SpecialOffer specialOffer = specialOfferMapper.toSpecialOffer(specialOfferDto);
        return specialOfferMapper.toSpecialOfferDto(specialOfferRepository.save(specialOffer));
    }

    @Override
    public SpecialOfferDto updateSpecialOffer(Long id, SpecialOfferDto specialOfferDto) {
        SpecialOffer specialOffer = specialOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Special offer with id " + id + " not found"));
        specialOffer.setOffer(specialOfferDto.getOffer());
        return specialOfferMapper.toSpecialOfferDto(specialOfferRepository.save(specialOffer));
    }

    @Override
    public String deleteSpecialOffer(Long id) {
        if(specialOfferRepository.findById(id).isPresent()) {
            specialOfferRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Review " + id + " not found!";
    }
}
