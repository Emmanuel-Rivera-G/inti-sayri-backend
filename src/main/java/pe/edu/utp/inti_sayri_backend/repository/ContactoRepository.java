package pe.edu.utp.inti_sayri_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.inti_sayri_backend.model.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    
}
