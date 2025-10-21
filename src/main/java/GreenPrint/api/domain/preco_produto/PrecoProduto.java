package GreenPrint.api.domain.preco_produto;

import GreenPrint.api.domain.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "preco_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPreco")
public class PrecoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPreco;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    private BigDecimal valorCompra;
    private BigDecimal valorVenda;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PrecoProduto(Produto produto, @NotNull BigDecimal bigDecimal, @NotNull BigDecimal bigDecimal1) {
        this.produto = produto;
        this.valorCompra = bigDecimal;
        this.valorVenda = bigDecimal1;
        this.dataInicio = LocalDate.now();
    }
}
