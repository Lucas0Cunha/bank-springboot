package com.bank.bank.controller;

import javax.naming.AuthenticationException;

import com.bank.bank.configuration.TokenService;
import com.bank.bank.dto.LoginDTO;
import com.bank.bank.dto.RegisterDTO;
import com.bank.bank.models.User;
import com.bank.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) throws AuthenticationException {
        if (this.userRepository.findByLogin(registerDTO.login()) != null)
            return ResponseEntity.badRequest().body("Login já em uso. Escolha outro login.");
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var usuario = new User(registerDTO.login(), encryptedPassword, registerDTO.role());
        this.userRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());

        var authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var usuario = (User) authenticate.getPrincipal();

        String token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(token);
    }
}