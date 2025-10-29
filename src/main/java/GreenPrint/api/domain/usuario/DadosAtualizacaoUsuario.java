package GreenPrint.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosAtualizacaoUsuario(
        @NotNull Long id,
        @NotBlank String nome,
        @NotBlank @Email String email,
        String telefone
) {}
