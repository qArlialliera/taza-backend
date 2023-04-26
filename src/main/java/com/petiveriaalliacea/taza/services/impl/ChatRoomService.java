package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.chat.ChatRoom;
import com.petiveriaalliacea.taza.repositories.ChatRoomRepository;
import com.petiveriaalliacea.taza.services.IChatRoomService;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class ChatRoomService implements IChatRoomService {
    private ChatRoomRepository chatRoomRepository;
    @Override
    public Optional<Long> getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId.toString(), recipientId.toString());

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(Long.valueOf(chatId))
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(Long.valueOf(chatId))
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(Long.valueOf(chatId));
                });

    }
}
