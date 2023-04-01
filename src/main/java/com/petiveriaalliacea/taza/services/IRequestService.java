package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.RequestDto;

import java.util.List;

public interface IRequestService {
    List<RequestDto> getRequests();
    RequestDto getRequest(Long id);
    RequestDto addNewRequest(RequestDto requestDto);
    RequestDto editRequest(Long id, RequestDto requestDto);
    String deleteRequest(Long id);
}
