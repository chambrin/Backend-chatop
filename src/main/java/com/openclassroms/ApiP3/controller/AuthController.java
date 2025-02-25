package com.openclassroms.ApiP3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroms.ApiP3.dto.LoginDTO;
import com.openclassroms.ApiP3.dto.RegisterDTO;
import com.openclassroms.ApiP3.dto.TokenResponseDTO;
import com.openclassroms.ApiP3.dto.UserDTO;
import com.openclassroms.ApiP3.mapper.UserMapper;
import com.openclassroms.ApiP3.model.AppUser;
import com.openclassroms.ApiP3.model.RegisterResponse;
import com.openclassroms.ApiP3.service.AuthService;
import com.openclassroms.ApiP3.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controller d'authentification", description = "On n'y retrouve les routes pour s'authentifier ")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;  // Injection du UserMapper

    /**
     * Endpoint pour l'enregistrement des utilisateurs
     *
     * @param registerDTO
     * @return
     */
    @Operation(summary = "Enregistrement d'un utilisateur", description = "Permet à un utilisateur de s'inscrire avec un email et un mot de passe.")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        RegisterResponse authResponse = userService.registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    // Endpoint pour la connexion des utilisateurs
    @Operation(summary = "Connexion d'un utilisateur", description = "Permet à un utilisateur de se connecter avec son email et mot de passe et obtenir un token JWT.")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO loginRequest) {
        TokenResponseDTO response = authService.authenticateUser(
                loginRequest.getEmail(), loginRequest.getPassword());

        if ("Invalid username or password".equals(response.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }

    // Endpoint pour récupérer les informations de l'utilisateur connecté
    @Operation(summary = "Récupérer les informations de l'user connecté", description = "Retourne les détails de l'utilisateur actuellement connecté en utilisant le token JWT.")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        AppUser currentUser = userService.getCurrentUser(username);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserDTO userDTO = userMapper.toDto(currentUser);  // Utilisation du UserMapper
        return ResponseEntity.ok(userDTO);
    }
}
