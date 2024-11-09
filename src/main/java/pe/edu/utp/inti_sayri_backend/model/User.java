package pe.edu.utp.inti_sayri_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Data
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
    @JsonBackReference
    private List<Chat> chats;
}
