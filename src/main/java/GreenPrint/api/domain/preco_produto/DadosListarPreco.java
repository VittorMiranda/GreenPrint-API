package GreenPrint.api.domain.preco_produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListarPreco(
        BigDecimal valorCompra,
        BigDecimal valorVenda,
        LocalDate dataInicio,
        LocalDate dataFim
) {
    public DadosListarPreco(PrecoProduto preco) {
        this(
                preco.getValorCompra(),
                preco.getValorVenda(),
                preco.getDataInicio(),
                preco.getDataFim()
        );
    }
}
