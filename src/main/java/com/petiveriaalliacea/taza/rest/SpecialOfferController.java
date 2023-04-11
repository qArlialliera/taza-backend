package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.SpecialOfferDto;
import com.petiveriaalliacea.taza.services.impl.SpecialOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/offers")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")

public class SpecialOfferController {
    private final SpecialOfferService specialOfferService;

    @GetMapping("/{id}")
    public ResponseEntity<SpecialOfferDto> getSpecialOfferById(@PathVariable Long id) {
        SpecialOfferDto specialOfferDto = specialOfferService.getSpecialOfferById(id);
        return ResponseEntity.ok(specialOfferDto);
    }

    @GetMapping
    public ResponseEntity<List<SpecialOfferDto>> getAllSpecialOffers() {
        List<SpecialOfferDto> specialOfferDtos = specialOfferService.getAllSpecialOffers();
        return ResponseEntity.ok(specialOfferDtos);
    }

    @PostMapping
    public ResponseEntity<SpecialOfferDto> saveSpecialOffer(@RequestBody SpecialOfferDto specialOfferDto) {
        specialOfferService.saveSpecialOffer(specialOfferDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialOfferDto> updateSpecialOffer(@PathVariable Long id, @RequestBody SpecialOfferDto specialOfferDto) {
        SpecialOfferDto specialOffer = specialOfferService.updateSpecialOffer(id, specialOfferDto);
        return ResponseEntity.ok(specialOffer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecialOffer(@PathVariable Long id) {
        return ResponseEntity.ok(specialOfferService.deleteSpecialOffer(id));
    }
}
