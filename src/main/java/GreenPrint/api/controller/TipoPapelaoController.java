package GreenPrint.api.controller;


import GreenPrint.api.tipo_papelao.DadosCadastroTipoPapelao;
import GreenPrint.api.tipo_papelao.TipoPapelao;
import GreenPrint.api.tipo_papelao.TipoPapelaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tipo_papelao")


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
    public List<TipoPapelao> listar(){
        return  tipoPapelaoRepository.findAll();

    }
}
