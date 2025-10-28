package GreenPrint.api.controller;

import GreenPrint.api.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

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

    // Listar todos usuários
    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        var usuarios = repository.findAll()
                .stream()
                .map(DadosDetalheUsuario::new) // converte cada Usuario em DTO
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    // Detalhar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> detalharUsuario(@PathVariable Long id) {
        var usuario = repository.findById(id);
        if (usuario.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DadosDetalheUsuario(usuario.get()));
    }

    @PutMapping("/papel")
    @Transactional
    public ResponseEntity<?> atualizarPapel(@RequestBody @Valid DadosAtualizacaoPapel dados) {
        var usuarioOpt = repository.findById(dados.id());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var usuario = usuarioOpt.get();
        usuario.setPapel(dados.papel());
        repository.save(usuario);

        return ResponseEntity.ok("Papel do usuário atualizado com sucesso!");
    }

}
