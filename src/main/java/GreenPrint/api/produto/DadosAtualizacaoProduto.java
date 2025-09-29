package GreenPrint.api.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
        @NotNull
        Long id,
        Integer quantidadeEstoque) {

}
