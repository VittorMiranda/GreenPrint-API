package GreenPrint.api.controller;

import GreenPrint.api.produto.DadosCadastroProduto;
import GreenPrint.api.produto.Produto;
import GreenPrint.api.produto.ProdutoRepository;
import GreenPrint.api.produto.TipoPapelao;
import GreenPrint.api.produto.TipoPapelaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private TipoPapelaoRepository tipoPapelaoRepository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroProduto dados){
        // Busca a entidade TipoPapelao pelo ID do DTO
        TipoPapelao tipo = tipoPapelaoRepository.findById(dados.idTipoPapelao())
                .orElseThrow(() -> new RuntimeException("Tipo de papelão não encontrado"));

        // Cria o Produto usando o construtor que recebe DTO + TipoPapelao
        Produto produto = new Produto(dados, tipo);

        repository.save(produto);
    }
}
