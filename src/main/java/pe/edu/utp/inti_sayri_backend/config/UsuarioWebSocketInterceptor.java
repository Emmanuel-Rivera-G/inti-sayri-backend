package pe.edu.utp.inti_sayri_backend.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import pe.edu.utp.inti_sayri_backend.websocket.model.WebSocketUserPrincipal;

@Component
public class UsuarioWebSocketInterceptor implements ChannelInterceptor {
    
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String userId = accessor.getFirstNativeHeader("userId");
            if (userId != null) {
                accessor.setUser(new WebSocketUserPrincipal(userId));
            } else {
                accessor.setUser(new WebSocketUserPrincipal("defaultUser"));
            }
        }

        return message;
    }
}
