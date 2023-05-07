package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
    Optional<List<ChatRoom>> findAllBySenderId(Long senderId);


    List<ChatRoom> findAllBySenderIdAndAndRecipientId(Long senderId, Long recipientId);


}
