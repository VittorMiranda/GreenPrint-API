package GreenPrint.api.domain.tipo_papelao;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tipo_papelao")
@Entity(name = "tipo_papelao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idTipoPapelao")
public class TipoPapelao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPapelao;
    private String nome;
    private String descricao;

    public TipoPapelao(DadosCadastroTipoPapelao dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
    }

    public void atualizarInformacoes(DadosAtualizarTipoPapelao dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }
    }
}
