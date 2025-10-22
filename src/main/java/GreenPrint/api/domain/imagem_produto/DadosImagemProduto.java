package GreenPrint.api.domain.imagem_produto;

import jakarta.validation.constraints.NotNull;

import java.util.Base64;

public record DadosImagemProduto(
        @NotNull
        byte[] arquivoImagem,
        String tipoImagem
) {
        public String getArquivoBase64() {
                return Base64.getEncoder().encodeToString(arquivoImagem);
        }
}
