package GreenPrint.api.domain.produto;

public record DadosListagemProduto(Long id, String nome, String altura, String largura, String profundidade, Integer quantidadeEstoque) {

    public DadosListagemProduto(Produto produto){
        this(produto.getIdProduto(), produto.getNome(), produto.getAltura(), produto.getLargura(), produto.getProfundidade(), produto.getQuantidadeEstoque());
    }

}
