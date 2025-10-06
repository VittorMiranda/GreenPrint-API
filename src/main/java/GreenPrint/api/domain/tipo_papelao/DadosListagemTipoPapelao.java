package GreenPrint.api.domain.tipo_papelao;

public record DadosListagemTipoPapelao(Long id, String nome, String descricao) {

    public DadosListagemTipoPapelao(TipoPapelao tipoPapelao){
        this(tipoPapelao.getIdTipoPapelao(), tipoPapelao.getNome(), tipoPapelao.getDescricao());
    }
}
