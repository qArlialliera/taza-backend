package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.ChatMessageDto;
import com.petiveriaalliacea.taza.dto.ChatRoomUserDto;
import com.petiveriaalliacea.taza.dto.UserViewDto;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.ChatRoom;
import com.petiveriaalliacea.taza.entities.chat.MessageStatus;
import com.petiveriaalliacea.taza.repositories.ChatMessageRepository;
import com.petiveriaalliacea.taza.repositories.ChatRoomRepository;
import com.petiveriaalliacea.taza.repositories.UserRepository;
import com.petiveriaalliacea.taza.security.JwtUtils;
import com.petiveriaalliacea.taza.services.IChatMessageService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatMessageService implements IChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.DELIVERED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public List<ChatMessageDto> changeStatus(String recipientToken, Long senderId) {
        User recipient = userRepository.findByUsername(JwtUtils.getUsername(recipientToken))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        List<ChatMessage> allMessages = chatMessageRepository.findAllBySenderIdAndAndRecipientIdAndStatus(senderId, recipient.getId(), MessageStatus.DELIVERED);
        allMessages.forEach((chatMessage) -> {
            chatMessage.setStatus(MessageStatus.DELIVERED);
            chatMessageRepository.save(chatMessage);
        });
        return allMessages
                .stream()
                .map(mapper::toChatMessageDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessageDto> findChatMessages(String senderToken, Long recipientId) {
        User user = userRepository.findByUsername(JwtUtils.getUsername(senderToken))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        List<ChatMessage> allMessages = chatMessageRepository.findAllBySenderIdAndAndRecipientId(user.getId(), recipientId);
        allMessages.addAll(chatMessageRepository.findAllBySenderIdAndAndRecipientId(recipientId, user.getId()));

        return allMessages
                .stream()
                .map(mapper::toChatMessageDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ChatRoomUserDto> getAllChatRooms(String token) {
        User user = userRepository.findByUsername(JwtUtils.getUsername(token))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        Optional<List<ChatRoom>> allChat = chatRoomRepository.findAllBySenderId(user.getId());
        List<ChatRoomUserDto> companions = new ArrayList<>();
        if(allChat.isPresent())
            for (ChatRoom room : allChat.get()) {
                User current = userRepository.findById(room.getRecipientId()).get();
                ChatMessage message = chatMessageRepository.findTopByChatIdOrderByIdDesc(room.getChatId());
                ChatRoomUserDto chatRoomUserDto = new ChatRoomUserDto();
                chatRoomUserDto.setId(current.getId());
                chatRoomUserDto.setChatId(room.getChatId());
                chatRoomUserDto.setUsername(current.getUsername());
                chatRoomUserDto.setFullName(current.getFullName());
                chatRoomUserDto.setPhoto(current.getPhoto());
                chatRoomUserDto.setMessage(message.getContent());
                chatRoomUserDto.setTimestamp(message.getTimestamp());
                companions.add(chatRoomUserDto);
            }
        return companions;
    }

    @Override
    public ChatMessageDto editMessage(Long id, String message) {
        Optional<ChatMessage> chatMessage = chatMessageRepository.findById(id);
        if(chatMessage.isPresent()){
            if (!StringUtils.isEmpty(message)) {
                chatMessage.get().setContent(message);
            }
        }
        return mapper.toChatMessageDto(chatMessageRepository.save(chatMessage.get()));
    }

    @Override
    public String deleteMessage(Long id) {
        if(chatMessageRepository.findById(id).isPresent()) {
            chatMessageRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Company not found!";
    }

    @Override
    public ChatMessage findById(String id) {
        return null;
    }

    @Override
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {

    }
}
