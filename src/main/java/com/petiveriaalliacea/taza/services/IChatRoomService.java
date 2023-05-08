package com.petiveriaalliacea.taza.services;

import java.util.Optional;

public interface IChatRoomService {
    Optional<String> getChatId(Long senderId, Long recipientId);

    Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist);
}
