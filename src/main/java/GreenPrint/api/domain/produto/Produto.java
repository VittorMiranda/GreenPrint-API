package GreenPrint.api.domain.produto;

import GreenPrint.api.domain.imagem_produto.ImagemProduto;
import GreenPrint.api.domain.preco_produto.PrecoProduto;
import GreenPrint.api.domain.tipo_papelao.TipoPapelao;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrecoProduto> precos = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagemProduto> imagens = new ArrayList<>();


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

    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
        if (dados.nome().equals(this.nome)) {
            this.nome = dados.nome();
        }
        if (dados.altura().equals(this.altura)) {
            this.altura = dados.altura();
        }
        if (dados.largura().equals(this.largura)) {
            this.largura = dados.largura();
        }
        if (dados.profundidade().equals(this.profundidade)) {
            this.profundidade = dados.profundidade();
        }
        if (dados.volumeSuportado().equals(this.volumeSuportado)) {
            this.volumeSuportado = dados.volumeSuportado();
        }
        if (dados.quantidadeEstoque() != null) {
            this.quantidadeEstoque = dados.quantidadeEstoque();
        }
        if (dados.cor() != null) {
            this.cor = dados.cor();
        }
        if (dados.nomeProjeto() != null) {
            this.projetoPrincipalNome = dados.nomeProjeto();
        }
        if (dados.descricaoProjeto() != null) {
            this.projetoPrincipalDescricao = dados.descricaoProjeto();
        }
    }
}
