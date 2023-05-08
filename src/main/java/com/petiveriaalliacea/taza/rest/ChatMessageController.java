package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.ChatMessageDto;
import com.petiveriaalliacea.taza.dto.ChatRoomUserDto;
import com.petiveriaalliacea.taza.dto.UserViewDto;
import com.petiveriaalliacea.taza.services.impl.ChatMessageService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/messages")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    @GetMapping("/{recipientId}")
    public ResponseEntity<List<ChatMessageDto>> getAllMessages(@RequestHeader("Authorization") @Parameter(hidden = true) String token, @PathVariable Long recipientId){
        return ResponseEntity.ok().body(chatMessageService.findChatMessages(token, recipientId));
    }
    @GetMapping("/chat-rooms")
    public ResponseEntity<List<ChatRoomUserDto>> getAllChatRooms(@RequestHeader("Authorization") @Parameter(hidden = true) String token){
        return ResponseEntity.ok().body(chatMessageService.getAllChatRooms(token));
    }
    @PutMapping("change-status/{senderId}")
    public ResponseEntity<List<ChatMessageDto>> changeStatus(@RequestHeader("Authorization") @Parameter(hidden = true) String token, @PathVariable Long senderId){
        return ResponseEntity.ok(chatMessageService.changeStatus(token, senderId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ChatMessageDto> editMessages(@RequestBody String message, @PathVariable Long id){
        return ResponseEntity.ok(chatMessageService.editMessage(id, message));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteMessages(@PathVariable Long id){
        return ResponseEntity.ok(chatMessageService.deleteMessage(id));
    }
}
