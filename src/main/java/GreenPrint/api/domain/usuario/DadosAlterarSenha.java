package GreenPrint.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAlterarSenha(
        @NotBlank String senhaAtual,
        @NotBlank String senhaNova
) {}
