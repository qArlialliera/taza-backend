package com.petiveriaalliacea.taza.entities.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;
}
