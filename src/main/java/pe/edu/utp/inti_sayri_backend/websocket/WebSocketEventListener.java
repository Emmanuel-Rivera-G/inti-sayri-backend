package pe.edu.utp.inti_sayri_backend.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import pe.edu.utp.inti_sayri_backend.model.Message;
import pe.edu.utp.inti_sayri_backend.model.User;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    
    private final SimpMessageSendingOperations messageTemplate;
    
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            User remitente = new User();
            remitente.setNombreCompleto(username);
            Message mensaje = new Message();
            mensaje.setRemitente(remitente);
            messageTemplate.convertAndSend("/topic/public", mensaje);
        }
    }
            
}
