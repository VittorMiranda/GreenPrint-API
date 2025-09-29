package GreenPrint.api.produto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProduto(
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
        @NotBlank
        Integer volumeSuportado,
        @NotBlank
        Integer quantidadeEstoque,
        @NotBlank
        Long idTipoPapelao
) {}

