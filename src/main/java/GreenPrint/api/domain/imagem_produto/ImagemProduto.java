package GreenPrint.api.domain.imagem_produto;

import GreenPrint.api.domain.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "imagem_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idImagem")
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagem;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Lob
    private byte[] arquivoImagem;

    private String tipoImagem;

    public ImagemProduto(Produto produto, @NotBlank byte[] imagem, String tipoImagem) {
        this.produto = produto;
        this.arquivoImagem = imagem;
        this.tipoImagem = tipoImagem;
    }
}
