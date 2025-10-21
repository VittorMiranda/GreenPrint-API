package GreenPrint.api.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String altura,
        @NotBlank
        String largura,
        @NotBlank
        String profundidade,
        @NotBlank
        String cor,
        @NotBlank
        String nomeProjeto,
        String descricaoProjeto,
        @NotNull
        Integer volumeSuportado,
        @NotNull
        Integer quantidadeEstoque,
        @NotBlank
        Long idTipoPapelao
) {

}
