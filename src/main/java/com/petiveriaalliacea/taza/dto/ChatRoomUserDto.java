package com.petiveriaalliacea.taza.dto;

import com.petiveriaalliacea.taza.entities.chat.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUserDto {
    private Long id;
    private String chatId;
    private String username;
    private String fullName;
    private UUID photo;
    private String message;
    private MessageStatus status;
    private Date timestamp;
    private int newMessagesCount;

}
