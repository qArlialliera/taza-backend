package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.services.impl.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/companies")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok().body(companyService.getCompanies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompany(id));
    }
    @GetMapping("category/{id}")
    public ResponseEntity<List<Company>> getCompaniesByCategory(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompaniesByCategory(id));
    }
    @PostMapping("/add")
    public ResponseEntity<Company> addNewCompany(@RequestBody CompanyRequestDto companyDto){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/companies/add").toUriString());
        return ResponseEntity.created(uri).body(companyService.addNewCompany(companyDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> editCompany(@RequestBody Company company, @PathVariable Long id){
        return ResponseEntity.ok(companyService.editCompany(id, company));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }


}
