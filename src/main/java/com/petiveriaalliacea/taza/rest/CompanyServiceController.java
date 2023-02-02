package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.CompanyService;
import com.petiveriaalliacea.taza.services.impl.CompanyServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")

public class CompanyServiceController {
    private final CompanyServiceService compService;
    @GetMapping("/company/{id}")
    public ResponseEntity<List<Category>> getCompanyServices(@PathVariable Long id){
        return ResponseEntity.ok(compService.getCompanyServices(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable Long id){
        return ResponseEntity.ok(compService.getPrice(id));
    }
    @PostMapping("/add")
    public ResponseEntity<CompanyService> addNewService(@RequestBody CompanyService companyService) {
        return ResponseEntity.ok(compService.addNewService(companyService));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompanyService> editServicePrice(@RequestBody BigDecimal price, @PathVariable Long id) {
        return ResponseEntity.ok(compService.editServicePrice(id, price));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteService(@PathVariable Long id) {
        return ResponseEntity.ok(compService.deleteService(id));
    }
}
