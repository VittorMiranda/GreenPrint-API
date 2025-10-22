package GreenPrint.api.controller;


import GreenPrint.api.domain.tipo_papelao.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_papelao")


public class TipoPapelaoController {
    @Autowired
    private TipoPapelaoRepository tipoPapelaoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTipoPapelao dados){
        TipoPapelao tipoPapelao = new TipoPapelao(dados);
        tipoPapelaoRepository.save(tipoPapelao);
    }

    @GetMapping
    public Page<DadosListagemTipoPapelao> listar(@PageableDefault(sort = {"nome"}) Pageable pageable){
        return  tipoPapelaoRepository.findAll(pageable).map(DadosListagemTipoPapelao::new);

    }

    @PutMapping
    @Transactional
    public void atualizarTipoPapelao(@RequestBody @Valid DadosAtualizarTipoPapelao dados){
        var tipoPapelao = tipoPapelaoRepository.getReferenceById(dados.id());
        tipoPapelao.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        tipoPapelaoRepository.deleteById(id);
    }
}
