package pe.edu.utp.inti_sayri_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.inti_sayri_backend.service.FavoritoService;

@RestController
@RequestMapping("/api/loction-favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping("/add")
    public String addFavorito(@RequestParam(value = "user-id") Long userId, @RequestParam(value = "location-id") String locationId) {
        return favoritoService.addFavorito(userId, locationId);
    }
    
    @PostMapping("/{userId}/add/{locationId}")
    public String addUrlPathFavorito(@PathVariable("userId") Long userId, @PathVariable(value = "locationId") String locationId) {
        return favoritoService.addFavorito(userId, locationId);
    }

    @DeleteMapping("/remove")
    public String removeFavorito(@RequestParam(value = "user-id") Long userId, @RequestParam(value = "location-id") String locationId) {
        return favoritoService.removeFavorito(userId, locationId);
    }
    
    @DeleteMapping("/{userId}/remove/{locationId}")
    public String removeUrlPathFavorito(@PathVariable("userId") Long userId, @PathVariable(value = "locationId") String locationId) {
        return favoritoService.removeFavorito(userId, locationId);
    }
}
