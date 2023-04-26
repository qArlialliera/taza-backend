package com.petiveriaalliacea.taza.services;

import java.util.Optional;

public interface IChatRoomService {
    Optional<Long> getChatId(Long senderId, Long recipientId, boolean createIfNotExist);
}
