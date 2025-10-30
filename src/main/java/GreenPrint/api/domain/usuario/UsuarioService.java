package GreenPrint.api.domain.usuario;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void alterarSenha(Long idUsuario, DadosAlterarSenha dados) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Confere a senha antiga
        if (!passwordEncoder.matches(dados.senhaAtual(), usuario.getSenha())) {
            throw new RuntimeException("Senha antiga incorreta!");
        }

        // Salva nova senha criptografada
        usuario.setSenha(passwordEncoder.encode(dados.senhaNova()));
    }
}
