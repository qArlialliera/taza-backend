package com.petiveriaalliacea.taza.entities.chat;

import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;

@Table(name = DATABASE_PREFIX + "chatroom")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom extends BaseEntity<Long> {
    private Long chatId;
    private Long senderId;
    private Long recipientId;
}