package pe.edu.utp.inti_sayri_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.inti_sayri_backend.model.Alerta;
import pe.edu.utp.inti_sayri_backend.repository.AlertaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public List<Alerta> getAllAlertas() {
        return alertaRepository.findAll();
    }

    public Optional<Alerta> getAlertaById(Long id) {
        return alertaRepository.findById(id);
    }

    public Alerta createAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public Alerta updateAlerta(Long id, Alerta alerta) {
        if (alertaRepository.existsById(id)) {
            alerta.setId(id);
            return alertaRepository.save(alerta);
        }
        return null;
    }

    public void deleteAlerta(Long id) {
        alertaRepository.deleteById(id);
    }
}
