package GreenPrint.api.controller;

import GreenPrint.api.domain.usuario.DadosAutenmticacao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;

    public AutenticacaoController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenmticacao dados) {
        try {
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(dados.email(), dados.senha())
            );

            // Login bem-sucedido
            return ResponseEntity.ok().body("{\"mensagem\": \"Login realizado com sucesso!\"}");
        } catch (BadCredentialsException e) {
            // Senha ou usuário incorreto
            return ResponseEntity.status(401).body("{\"erro\": \"Usuário ou senha inválidos.\"}");
        }
    }
}
