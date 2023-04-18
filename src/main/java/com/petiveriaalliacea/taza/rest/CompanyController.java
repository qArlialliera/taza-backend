package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.CompanyDto;
import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.services.impl.CompanyService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/companies")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        return ResponseEntity.ok().body(companyService.getCompanies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompany(id));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<CompanyDto>> getCompaniesByCategory(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompaniesByCategory(id));
    }
    @GetMapping("/user")
    public ResponseEntity<CompanyDto> getCompaniesByUser(@RequestHeader("Authorization") @Parameter(hidden = true) String token){
        return ResponseEntity.ok(companyService.getUserCompany(token));
    }
    @GetMapping("/exist-for-user")
    public ResponseEntity<Boolean> companyExistForUser(@RequestHeader("Authorization") @Parameter(hidden = true) String token){
        return ResponseEntity.ok(companyService.getUserCompanyExist(token));
    }
    @PostMapping("/add")
    public ResponseEntity<CompanyDto> addNewCompany(@RequestBody CompanyRequestDto companyDto){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/companies/add").toUriString());
        return ResponseEntity.created(uri).body(companyService.addNewCompany(companyDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> editCompany(@RequestBody CompanyDto company, @PathVariable Long id){
        return ResponseEntity.ok(companyService.editCompany(id, company));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
    @PutMapping("/photo/upload/{id}/{uuid}")
    public void uploadPhoto(@PathVariable("id") Long id, @PathVariable("uuid") UUID photo) {
        companyService.addPhoto(id, photo);
    }

    @GetMapping("/photo/get/{id}")
    public ResponseEntity<UUID> getPhoto(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getPhoto(id));
    }

}
