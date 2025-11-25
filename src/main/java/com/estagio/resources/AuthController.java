package com.estagio.resources;

import com.estagio.domains.Cliente;
import com.estagio.domains.dtos.CredenciaisDTO;
import com.estagio.domains.dtos.TokenDTO;
import com.estagio.domains.dtos.ClienteRegisterDTO;
import com.estagio.domains.enums.TipoUsuario;
import com.estagio.repositories.UsuarioRepository;
import com.estagio.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    // -----------------------------------
    //  LOGIN
    // -----------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredenciaisDTO credenciais){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credenciais.getUsername(),
                            credenciais.getPassword()
                    )
            );

            String token = jwtUtils.generateToken(credenciais.getUsername());
            return ResponseEntity.ok(new TokenDTO(token));

        } catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciais inválidas.");
        }
    }

    // -----------------------------------
    //  REGISTER (CADASTRO DE CLIENTE)
    // -----------------------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClienteRegisterDTO dto) {

        // Verifica email duplicado
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail já cadastrado.");
        }

        // Criar instância concreta Cliente
        Cliente cliente = new Cliente();
        cliente.setFirstName(dto.getFirstName());
        cliente.setLastName(dto.getLastName());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setPassword(passwordEncoder.encode(dto.getPassword()));
        cliente.setTipoUsuario(TipoUsuario.CLIENTE);

        usuarioRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente registrado com sucesso!");
    }

}
