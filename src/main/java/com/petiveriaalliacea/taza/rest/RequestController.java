package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.RequestDto;
import com.petiveriaalliacea.taza.services.impl.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/requests")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class RequestController {
    private final RequestService requestService;
    
    @GetMapping("/all")
    public ResponseEntity<List<RequestDto>> getAllCompanies(){
        return ResponseEntity.ok().body(requestService.getRequests());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(requestService.getRequest(id));
    }
    @PostMapping("/add")
    public ResponseEntity<RequestDto> addNewCompany(@RequestBody RequestDto requestDto){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/companies/add").toUriString());
        return ResponseEntity.created(uri).body(requestService.addNewRequest(requestDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RequestDto> editCompany(@RequestBody RequestDto request, @PathVariable Long id){
        return ResponseEntity.ok(requestService.editRequest(id, request));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.deleteRequest(id));
    }
}
