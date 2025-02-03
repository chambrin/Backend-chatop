package com.openclassroms.ApiP3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroms.ApiP3.dto.UserDTO;
import com.openclassroms.ApiP3.mapper.UserMapper;
import com.openclassroms.ApiP3.model.AppUser;
import com.openclassroms.ApiP3.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Contrôleur des utilisateurs", description = "Gestion des opérations liées aux utilisateurs (récupérer les informations d'un utilisateur).")
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper; // Injection du UserMapper

    /**
     * @param id
     * @return ResponseEntity<UserDTO>
     */
    @Operation(summary = "Récupérer un utilisateur par ID", description = "Retourne les informations d'un utilisateur spécifié par son ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        AppUser user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = userMapper.toDto(user); // Utilisation du UserMapper
        return ResponseEntity.ok(userDTO);
    }
}
