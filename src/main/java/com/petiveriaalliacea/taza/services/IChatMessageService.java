package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.ChatMessageDto;
import com.petiveriaalliacea.taza.dto.ChatRoomUserDto;
import com.petiveriaalliacea.taza.dto.UserViewDto;
import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.MessageStatus;

import java.util.List;

public interface IChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessageDto> changeStatus(String recipientToken, Long senderId);

    List<ChatMessageDto> findChatMessages(String senderId, Long recipientId);

    List<ChatRoomUserDto> getAllChatRooms(String token);

    ChatMessageDto editMessage(Long id, String message);

    String deleteMessage(Long id);

    ChatMessage findById(String id);
    void updateStatuses(String senderId, String recipientId, MessageStatus status);
}
