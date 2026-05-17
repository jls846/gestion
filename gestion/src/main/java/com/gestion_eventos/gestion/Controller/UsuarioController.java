package com.gestion_eventos.gestion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if(usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Usuario usuario) {
    
    java.util.Optional<Usuario> uOpt = usuarioRepository.findByUsername(usuario.getUsername());

    if (uOpt.isPresent() && uOpt.get().getPassword().equals(usuario.getPassword())) {
        return ResponseEntity.ok(uOpt.get()); 
    }

    return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
}
}