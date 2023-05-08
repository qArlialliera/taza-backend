package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findAllBySenderIdAndAndRecipientId(Long senderId, Long recipientId);

    List<ChatMessage> findAllBySenderIdAndAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

    ChatMessage findTopByChatIdOrderByIdDesc(String chatId);

}
