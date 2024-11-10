package pe.edu.utp.inti_sayri_backend.websocket.model;

import java.security.Principal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WebSocketUserPrincipal implements Principal {

    @Getter
    private final String name;
    
}
