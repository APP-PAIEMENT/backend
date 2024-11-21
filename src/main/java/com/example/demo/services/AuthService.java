package com.example.demo.services;

import com.example.demo.DTOs.AuthRequest;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User login(AuthRequest request) {
        // Trouver l'utilisateur par son email
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        // Vérifier si l'utilisateur existe
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Vérifier le mot de passe en utilisant PasswordEncoder
            if (request.getPassword().equals(user.getPassword())) {
                return user; // Authentification réussie
            } else {
                throw new RuntimeException("Invalid credentials: Incorrect password");
            }
        } else {
            throw new RuntimeException("Invalid credentials: User not found");
        }
    }

}
