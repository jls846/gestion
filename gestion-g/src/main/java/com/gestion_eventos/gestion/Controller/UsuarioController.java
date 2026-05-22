package com.gestion_eventos.gestion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Usuarios",
    description = "API para la gestión de usuarios y autenticación"
)

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(
        summary = "Registrar un nuevo usuario",
        description = "Permite registrar un nuevo usuario en el sistema"
    )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario registrado correctamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "El nombre de usuario ya existe"
        )
    })

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(
            
            @RequestBody Usuario usuario) {

        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {

            return ResponseEntity
                    .badRequest()
                    .body("El nombre de usuario ya existe");
        }

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @Operation(
        summary = "Inicio de sesión de usuario",
        description = "Permite autenticar un usuario mediante username y password"
    )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Inicio de sesión exitoso"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Usuario o contraseña incorrectos"
        )
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(
            
            @RequestBody Usuario usuario) {

        java.util.Optional<Usuario> uOpt =
                usuarioRepository.findByUsername(usuario.getUsername());

        if (uOpt.isPresent() &&
                uOpt.get().getPassword().equals(usuario.getPassword())) {

            return ResponseEntity.ok(uOpt.get());
        }

        return ResponseEntity
                .status(401)
                .body("Usuario o contraseña incorrectos");
    }
}