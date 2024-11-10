package pe.edu.utp.inti_sayri_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.inti_sayri_backend.model.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    
}
