package pe.edu.utp.inti_sayri_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.inti_sayri_backend.model.Chat;
import pe.edu.utp.inti_sayri_backend.model.Message;
import pe.edu.utp.inti_sayri_backend.service.ChatService;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import pe.edu.utp.inti_sayri_backend.util.ResponseUtil;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createChat(@RequestBody Chat chat) {
        Map<String, Object> response = chatService.createChat(chat);
        return ResponseUtil.createResponse(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllChats() {
        Map<String, Object> response = chatService.getAllChats();
        return ResponseUtil.createResponse(response, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Map<String, Object>> getChatById(@PathVariable Long chatId) {
        Map<String, Object> response = chatService.getChatById(chatId);
        return ResponseUtil.createResponse(response, HttpStatus.OK);
    }
    
    @PostMapping("/{chatId}/participants/{userId}")
    public ResponseEntity<Map<String, Object>> addParticipantToChat(@PathVariable Long chatId, @PathVariable Long userId) {
        Map<String, Object> response = chatService.addParticipantToChat(chatId, userId);
        return ResponseUtil.createResponse(response, HttpStatus.OK);
    }

    @PostMapping("/{chatId}/messages")
    public ResponseEntity<Map<String, Object>> addMessageToChat(@PathVariable Long chatId, @RequestBody Message message) {
        Map<String, Object> response = chatService.addMessageToChat(chatId, message);
        return ResponseUtil.createResponse(response, HttpStatus.OK);
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<Map<String, Object>> getMessagesByChatId(@PathVariable Long chatId) {
        Map<String, Object> response = chatService.getMessagesByChatId(chatId);
        return ResponseUtil.createResponse(response, HttpStatus.OK);
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Map<String, Object>> deleteChat(@PathVariable Long chatId) {
        Map<String, Object> response = chatService.deleteChat(chatId);
        return ResponseUtil.createResponse(response, HttpStatus.OK);
    }
}
