package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.RequestDto;
import com.petiveriaalliacea.taza.entities.Request;
import com.petiveriaalliacea.taza.mappers.RequestMapper;
import com.petiveriaalliacea.taza.repositories.RequestRepository;
import com.petiveriaalliacea.taza.services.IRequestService;
import com.petiveriaalliacea.taza.utils.StringUtils;
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
public class RequestService implements IRequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper mapper;

    @Override
    public List<RequestDto> getRequests() {
        return requestRepository.findAll()
                .stream()
                .map(request -> mapper.toRequestDto(request))
                .collect(Collectors.toList());
    }
    @Override
    public RequestDto getRequest(Long id) {
        Request request = requestRepository.getReferenceById(id);
        return mapper.toRequestDto(request);
    }

    @Override
    public RequestDto addNewRequest(RequestDto requestDto) {
        Request request = mapper.toRequest(requestDto);
        request.setCategories(requestDto.getCategories());

        return mapper.toRequestDto(requestRepository.save(request));
    }

    @Override
    public RequestDto editRequest(Long id, RequestDto requestDto) {
        Optional<Request> request = requestRepository.findById(id);
        if(request.isPresent()){
            if (!StringUtils.isEmpty(requestDto.getName())) {
                request.get().setName(requestDto.getName());
            }
            if (!StringUtils.isEmpty(requestDto.getEmail())) {
                request.get().setEmail(requestDto.getEmail());
            }
            if (!StringUtils.isEmpty(requestDto.getAddress())) {
                request.get().setAddress(requestDto.getAddress());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                request.get().setPhoneNumber(requestDto.getPhoneNumber());
            }
            if (!StringUtils.isEmpty(requestDto.getRemarks())) {
                request.get().setPhoneNumber(requestDto.getRemarks());
            }
            if (!StringUtils.isEmpty(requestDto.getCategorySuggest())) {
                request.get().setPhoneNumber(requestDto.getCategorySuggest());
            }
            if (!(requestDto.getCategories()).isEmpty()) {
                request.get().setCategories(requestDto.getCategories());
            }
        }
        return mapper.toRequestDto(requestRepository.save(request.get()));
    }

    @Override
    public String deleteRequest(Long id) {
        if(requestRepository.findById(id).isPresent()) {
            requestRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Request not found!";

    }
}
