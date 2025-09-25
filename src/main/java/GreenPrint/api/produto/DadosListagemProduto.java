package GreenPrint.api.produto;

public record DadosListagemProduto(String nome, String altura, String largura, String profundidade) {

    public DadosListagemProduto(Produto produto){
        this(produto.getNome(), produto.getAltura(), produto.getLargura(), produto.getProfundidade());
    }

}
