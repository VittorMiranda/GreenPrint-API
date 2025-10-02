package GreenPrint.api.tipo_papelao;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTipoPapelao(
        @NotNull
        Long id,
        String nome,
        String descricao
) {
}
