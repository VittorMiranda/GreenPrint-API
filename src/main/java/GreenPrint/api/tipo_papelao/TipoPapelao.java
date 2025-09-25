package GreenPrint.api.tipo_papelao;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tipo_papelao")
@Entity(name = "tipo_papelao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
}
