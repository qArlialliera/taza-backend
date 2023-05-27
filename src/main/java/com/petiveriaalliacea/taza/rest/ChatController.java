package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.ChatNotification;
import com.petiveriaalliacea.taza.services.impl.ChatMessageService;
import com.petiveriaalliacea.taza.services.impl.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/message") // same as addEndpoint
    @SendTo("/chatroom/public")
    public ChatMessage receivePublicMessage(@Payload ChatMessage chatMessage) {
        return chatMessageService.save(chatMessage);
    }

    @MessageMapping("/private-message")
    @SendTo("/chatroom/private")
    public ChatMessage receivePrivateMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId());
        chatMessage.setChatId(chatId.get());

        ChatMessage savedMessage = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientName(),
                "/private", savedMessage);
        return savedMessage;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId().toString(),
                "/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

}
