package GreenPrint.api.domain.preco_produto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizarPreco(
        BigDecimal valorCompra,
        BigDecimal valorVenda,
        LocalDate dataInicio,
        LocalDate dataFim
) {
    public DadosAtualizarPreco(PrecoProduto preco) {
        this(
                preco.getValorCompra(),
                preco.getValorVenda(),
                preco.getDataInicio(),
                preco.getDataFim()
        );
    }
}
