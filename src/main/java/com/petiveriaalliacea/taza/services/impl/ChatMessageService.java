package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.MessageStatus;
import com.petiveriaalliacea.taza.repositories.ChatMessageRepository;
import com.petiveriaalliacea.taza.services.IChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService implements IChatMessageService {
    private ChatMessageRepository repository;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        return null;
    }

//    @Override
//    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
//        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
//
//        var messages =
//                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
//
//        if(messages.size() > 0) {
//            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
//        }
//
//        return messages;
//
//    }

    @Override
    public ChatMessage findById(String id) {
        return null;
    }

    @Override
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {

    }
}
