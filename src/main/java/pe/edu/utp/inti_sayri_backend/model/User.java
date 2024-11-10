package pe.edu.utp.inti_sayri_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreCompleto;
    
    private String correoElectronico;
    
    private String contrasena;
    
    private String profilePhotoUrl;
    
    @ManyToMany(mappedBy = "participantes")
    private List<Chat> chats;
    
    @ManyToMany(mappedBy = "usuariosFavoritos")
    private List<Location> favoritos;
}
