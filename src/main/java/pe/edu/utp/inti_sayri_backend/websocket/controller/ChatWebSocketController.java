package pe.edu.utp.inti_sayri_backend.websocket.controller;

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
    
    @MessageMapping("/enviarMensaje")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message mensaje) {
        chatService.addMessageToChat(mensaje.getChat().getId(), mensaje);
        return mensaje;
    }
    
    @MessageMapping("/userMensaje")
    @SendTo("/queue/public")
    public Message addUser(@Payload Message mensaje, SimpMessageHeaderAccessor headerAccesor) {
        headerAccesor.getSessionAttributes().put("username", mensaje.getRemitente().getNombreCompleto());
        return mensaje;
    }

    public void enviarMensajePrivado(User destinatario, Message mensaje) {
        messagingTemplate.convertAndSendToUser(destinatario.getId().toString() , "/queue/mensajes", mensaje);
    }
}
