package GreenPrint.api.domain.produto;


import GreenPrint.api.domain.imagem_produto.DadosImagemProduto;
import GreenPrint.api.domain.imagem_produto.ImagemProduto;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoProduto(
        Long id,
        String nome,
        String cor,
        Integer altura,
        Integer largura,
        Integer profundidade,
        Integer volume,
        Integer quantidadeEstoque,
        Long idTipoPapelao,
        BigDecimal valorCompra,
        BigDecimal valorVenda,
        String nomeProjeto,
        String descricaoProjeto,
        List<DadosImagemProduto> imagens
) {

    public DadosDetalhamentoProduto(Produto produto) {
        this(
                produto.getIdProduto(),
                produto.getNome(),
                produto.getCor(),
                tryParseInt(produto.getAltura()),
                tryParseInt(produto.getLargura()),
                tryParseInt(produto.getProfundidade()),
                produto.getVolumeSuportado(),
                produto.getQuantidadeEstoque(),
                produto.getTipoPapelao().getIdTipoPapelao(),
                produto.getPrecos().getLast().getValorCompra(),
                produto.getPrecos().getLast().getValorVenda(),
                produto.getProjetoPrincipalNome(),
                produto.getProjetoPrincipalDescricao(),
                produto.getImagens() != null
                        ? produto.getImagens().stream()
                        .map(img -> new DadosImagemProduto(img.getArquivoImagem(), img.getTipoImagem()))
                        .collect(Collectors.toList())
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