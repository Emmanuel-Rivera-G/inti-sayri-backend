package pe.edu.utp.inti_sayri_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.inti_sayri_backend.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    
}
