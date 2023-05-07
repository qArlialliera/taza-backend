package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.chat.ChatRoom;
import com.petiveriaalliacea.taza.repositories.ChatRoomRepository;
import com.petiveriaalliacea.taza.services.IChatRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatRoomService implements IChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public Optional<Long> getChatId(Long senderId, Long recipientId) {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
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
