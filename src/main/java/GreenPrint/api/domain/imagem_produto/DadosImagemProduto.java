package GreenPrint.api.domain.imagem_produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Base64;

public record DadosImagemProduto(
        @NotNull
        @JsonIgnore
        byte[] arquivoImagem,
        String tipoImagem
) {
    // Construtor auxiliar que recebe string base64
    public DadosImagemProduto(String base64, String tipoImagem) {
        this(decodeBase64(base64), tipoImagem);
    }

    private static byte[] decodeBase64(String base64) {
        if (base64 == null) return null;
        // remove o prefixo "data:image/png;base64,"
        int commaIndex = base64.indexOf(",");
        if (commaIndex != -1) {
            base64 = base64.substring(commaIndex + 1);
        }
        return Base64.getDecoder().decode(base64);
    }

    @JsonIgnore
    public String getArquivoBase64() {
        return Base64.getEncoder().encodeToString(arquivoImagem);
    }

    @JsonProperty("imagemCompleta")
    public String getImagemCompleta() {
        return "data:" + tipoImagem + ";base64," + getArquivoBase64();
    }
}
