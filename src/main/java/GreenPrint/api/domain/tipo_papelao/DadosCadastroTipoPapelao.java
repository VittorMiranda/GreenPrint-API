package GreenPrint.api.domain.tipo_papelao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTipoPapelao(
        @NotBlank
        String nome,
        @NotBlank
        String descricao
) { }
