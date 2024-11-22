package com.example.demo.contollers;


import com.example.demo.models.Compte;
import com.example.demo.models.User;
import com.example.demo.services.CompteService;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CompteService compteService;

    // Endpoint pour créer un utilisateur
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // Endpoint pour récupérer tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint pour récupérer un utilisateur par ID
    @GetMapping("/compte/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        Optional<Compte> compteOptional =compteService.getComptesByNumerousCompte(id);
        if (compteOptional.isPresent()) {
            Compte compte =compteOptional.get();
            return userService.getUserById(compte.getUserId());
        } else {
            throw new RuntimeException("Invalid credentials: User not found");
        }
    }

    // Endpoint pour récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public Optional<User> getUserByNumberCompte(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // Endpoint pour mettre à jour un utilisateur
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @Valid @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Endpoint pour supprimer un utilisateur
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User with ID " + id + " has been deleted.";
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
//        try {
//            // Appel au service de login
//            String token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
//            return ResponseEntity.ok(new AuthResponse(token, "auth success"));
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
//        try {
//            // Extraction du token sans le préfixe "Bearer "
//            String rawToken = token.replace("Bearer ", "");
//            userService.logout(rawToken);
//            return ResponseEntity.ok("Déconnexion réussie");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }


}
