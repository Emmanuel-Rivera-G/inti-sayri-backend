package pe.edu.utp.inti_sayri_backend.websocket.controller;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import pe.edu.utp.inti_sayri_backend.model.Message;
import pe.edu.utp.inti_sayri_backend.model.User;
import pe.edu.utp.inti_sayri_backend.service.ChatService;

@Controller
public class ChatWebSocketController {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private ChatService chatService;
    
    private ConcurrentHashMap<String, String> userSessions = new ConcurrentHashMap<String, String>();
    
    @MessageMapping("/enviar-mensajes")
    @SendTo("/tema/public/recibir-mensaje")
    public Message sendMessage(@Payload Message mensaje) {
        chatService.addMessageToChat(mensaje.getChat().getId(), mensaje);
        return mensaje;
    }
    
    @MessageMapping("/add-user")
    @SendTo("/cola/public")
    public Message addUser(@Payload Message mensaje, SimpMessageHeaderAccessor headerAccesor) {
        String userId = mensaje.getRemitente().getId().toString();
        headerAccesor.getSessionAttributes().put("userId", userId);
        
        userSessions.put(userId, headerAccesor.getSessionId());

        return mensaje;
    }

    public void enviarMensajePrivado(User destinatario, Message mensaje) {
        String sessionId = userSessions.get(destinatario.getId().toString());
        if (sessionId != null) {
            messagingTemplate.convertAndSendToUser(sessionId, "/cola/mensajes-privados", mensaje);
        }
    }
    
    public void removeUserSession(String username) {
        userSessions.remove(username);
    }
}
