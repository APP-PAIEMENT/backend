package com.example.demo.contollers;

import com.example.demo.DTOs.AuthRequest;
import com.example.demo.models.User;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    @Autowired
    private AuthService authService;

    /**
     * Endpoint pour se connecter.
     *
     * @param request AuthRequest contenant email et mot de passe.
     * @return AuthResponse avec le token et un message.
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthRequest request) {
        User response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint pour se déconnecter.
     *
     * @param token Token JWT à invalider.
     * @return Message de succès.
     */
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
//        // Supprimer le préfixe "Bearer " si nécessaire
//        String tokenWithoutBearer = token.replace("Bearer ", "");
//        String message = userAuthService.logout(tokenWithoutBearer);
//        return ResponseEntity.ok(message);
//    }
}
