package pe.edu.utp.inti_sayri_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.inti_sayri_backend.model.Favorito;
import pe.edu.utp.inti_sayri_backend.model.Location;
import pe.edu.utp.inti_sayri_backend.model.User;
import pe.edu.utp.inti_sayri_backend.repository.FavoritoRepository;
import pe.edu.utp.inti_sayri_backend.repository.LocationRepository;
import pe.edu.utp.inti_sayri_backend.repository.UserRepository;

import java.util.Optional;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    public String addFavorito(Long userId, Long locationId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Location> locationOpt = locationRepository.findById(locationId);

        if (userOpt.isPresent() && locationOpt.isPresent()) {
            Favorito favorito = Favorito.builder()
                    .user(userOpt.get())
                    .location(locationOpt.get())
                    .build();
            favoritoRepository.save(favorito);
            return "Favorito agregado correctamente.";
        } else {
            return "Usuario o ubicación no encontrados.";
        }
    }

    public String removeFavorito(Long userId, Long locationId) {
        Optional<Favorito> favoritoOpt = favoritoRepository.findByUserIdAndLocationId(userId, locationId);
        if (favoritoOpt.isPresent()) {
            favoritoRepository.delete(favoritoOpt.get());
            return "Favorito eliminado correctamente.";
        } else {
            return "Favorito no encontrado.";
        }
    }
}
