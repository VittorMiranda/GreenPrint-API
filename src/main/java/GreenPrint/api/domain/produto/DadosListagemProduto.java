package GreenPrint.api.domain.produto;

import GreenPrint.api.domain.imagem_produto.DadosImagemProduto;
import GreenPrint.api.domain.imagem_produto.ImagemProduto;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemProduto(
        Long id,
        String nome,
        String cor,
        Integer altura,
        Integer largura,
        Integer profundidade,
        Integer volume,
        List<DadosImagemProduto> imagens
) {
    public DadosListagemProduto(Produto produto) {
        this(
                produto.getIdProduto(),
                produto.getNome(),
                produto.getCor(),
                tryParseInt(produto.getAltura()),
                tryParseInt(produto.getLargura()),
                tryParseInt(produto.getProfundidade()),
                produto.getVolumeSuportado(),
                produto.getImagens() != null
                        ? produto.getImagens().stream()
                        .map(img -> new DadosImagemProduto(img.getArquivoImagem(), img.getTipoImagem()))
                        .toList()
                        : List.of()
        );
    }

    private static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
