package GreenPrint.api.domain.produto;

import GreenPrint.api.domain.imagem_produto.DadosImagemProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

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
        Long idTipoPapelao,
        @NotNull
        BigDecimal valorCompra,
        @NotNull
        BigDecimal valorVenda,
        @NotNull
        List<DadosImagemProduto> imagens
) {

}
