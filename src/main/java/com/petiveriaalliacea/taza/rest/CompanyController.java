package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor

public class CompanyController {
    private CompanyService companyService;
    @GetMapping("/all")
    public ResponseEntity<List<Company>> getUsers(){
        return ResponseEntity.ok().body(companyService.getCompanies());
    }
}
