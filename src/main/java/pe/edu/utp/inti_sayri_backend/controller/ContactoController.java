package pe.edu.utp.inti_sayri_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.inti_sayri_backend.model.Contacto;
import pe.edu.utp.inti_sayri_backend.service.ContactoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    private final ContactoService contactoService;

    @Autowired
    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping
    public ResponseEntity<Contacto> crearContacto(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoService.crearContacto(contacto);
        return ResponseEntity.ok(nuevoContacto);
    }

    @GetMapping
    public ResponseEntity<List<Contacto>> obtenerTodosContactos() {
        List<Contacto> contactos = contactoService.obtenerTodosContactos();
        return ResponseEntity.ok(contactos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerContactoPorId(@PathVariable("id") Long id) {
        Optional<Contacto> contacto = contactoService.obtenerContactoPorId(id);
        return contacto.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable("id") Long id) {
        contactoService.eliminarContacto(id);
        return ResponseEntity.noContent().build();
    }
}