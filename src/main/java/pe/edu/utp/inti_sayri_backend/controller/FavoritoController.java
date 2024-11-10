package pe.edu.utp.inti_sayri_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.inti_sayri_backend.service.FavoritoService;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping("/add")
    public String addFavorito(@RequestParam Long userId, @RequestParam String locationId) {
        return favoritoService.addFavorito(userId, locationId);
    }

    @DeleteMapping("/remove")
    public String removeFavorito(@RequestParam Long userId, @RequestParam String locationId) {
        return favoritoService.removeFavorito(userId, locationId);
    }
}
