package GreenPrint.api.controller;

import GreenPrint.api.produto.*;
import GreenPrint.api.tipo_papelao.TipoPapelao;
import GreenPrint.api.tipo_papelao.TipoPapelaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private TipoPapelaoRepository tipoPapelaoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroProduto dados){
        // Busca a entidade TipoPapelao pelo ID do DTO
        TipoPapelao tipo = tipoPapelaoRepository.findById(dados.idTipoPapelao())
                .orElseThrow(() -> new RuntimeException("Tipo de papelão não encontrado"));

        // Cria o Produto usando o construtor que recebe DTO + TipoPapelao
        Produto produto = new Produto(dados, tipo);

        repository.save(produto);
    }

    @GetMapping
    public Page<DadosListagemProduto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAll(pageable).map(DadosListagemProduto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados){
        var produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
