package GreenPrint.api.produto;

public record DadosDetalhamentoProduto(Long id, String nome) {
    public DadosDetalhamentoProduto(Produto produto){
        this(produto.getIdProduto(), produto.getNome());
    }
}
