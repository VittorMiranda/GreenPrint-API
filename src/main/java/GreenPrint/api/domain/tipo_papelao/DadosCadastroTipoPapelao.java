package GreenPrint.api.domain.tipo_papelao;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTipoPapelao(
        @NotBlank
        String nome,
        @NotBlank
        String descricao
) { }
