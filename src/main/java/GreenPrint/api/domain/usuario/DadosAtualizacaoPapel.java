package GreenPrint.api.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPapel(
        @NotNull Long id,
        @NotNull Papel papel
) {}
