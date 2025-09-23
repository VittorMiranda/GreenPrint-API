package GreenPrint.api.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "produto")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idProduto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    private String nome;
    private String altura;
    private String largura;
    private String profundidade;
    private Integer volumeSuportado;
    private Integer quantidadeEstoque;
    private String cor;

    private String projetoPrincipalNome;
    private String projetoPrincipalDescricao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_papelao", nullable = false)
    private TipoPapelao tipoPapelao;

    public Produto(DadosCadastroProduto dados, TipoPapelao tipoPapelao) {
        this.nome = dados.nome();
        this.altura = dados.altura();
        this.largura = dados.largura();
        this.profundidade = dados.profundidade();
        this.volumeSuportado = dados.volumeSuportado();
        this.quantidadeEstoque = dados.quantidadeEstoque();
        this.cor = dados.cor();
        this.projetoPrincipalNome = dados.nomeProjeto();
        this.projetoPrincipalDescricao = dados.descricaoProjeto();
        this.tipoPapelao = tipoPapelao;
    }

}
