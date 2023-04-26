package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.MessageStatus;

import java.util.List;

public interface IChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findChatMessages(String senderId, String recipientId);
    ChatMessage findById(String id);
    void updateStatuses(String senderId, String recipientId, MessageStatus status);
}
