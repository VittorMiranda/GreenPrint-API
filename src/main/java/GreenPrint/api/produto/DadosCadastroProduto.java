package GreenPrint.api.produto;

public record DadosCadastroProduto(
        String nome,
        String altura,
        String largura,
        String profundidade,
        String cor,
        String nomeProjeto,
        String descricaoProjeto,
        Integer volumeSuportado,
        Integer quantidadeEstoque,
        Long idTipoPapelao
) {}

