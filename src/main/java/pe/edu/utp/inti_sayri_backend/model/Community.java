package pe.edu.utp.inti_sayri_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "communities")
public class Community {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @ManyToMany
    @JoinTable(
        name = "community_user",
        joinColumns = @JoinColumn(name = "community_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    
    @OneToMany(mappedBy = "community")
    private List<Alerta> alertas;
    
}
