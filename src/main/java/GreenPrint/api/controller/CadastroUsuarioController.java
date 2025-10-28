package GreenPrint.api.controller;

import GreenPrint.api.domain.usuario.DadosCadastroUsuario;
import GreenPrint.api.domain.usuario.Usuario;
import GreenPrint.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados) {
        if (repository.findByEmail(dados.email()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado!");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(
                null,
                dados.nome(),
                dados.email(),
                senhaCriptografada,
                dados.telefone(),
                dados.papel()
        );

        repository.save(usuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
