package GreenPrint.api.produto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tipo_papelao")
@Entity(name = "TipoPapelao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoPapelao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
}
