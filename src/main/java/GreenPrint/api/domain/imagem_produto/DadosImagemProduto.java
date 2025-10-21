package GreenPrint.api.domain.imagem_produto;

import jakarta.validation.constraints.NotNull;

public record DadosImagemProduto(
        @NotNull
        byte[] arquivoImagem,
        String tipoImagem
) {
}
