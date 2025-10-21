package GreenPrint.api.domain.preco_produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPrecoProduto(
        @NotNull
        BigDecimal valorCompra,
        @NotNull
        BigDecimal valorVenda,
        LocalDate dataInicio,
        LocalDate dataFim
) {

}
