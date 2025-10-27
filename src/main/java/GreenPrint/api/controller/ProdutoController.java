package GreenPrint.api.controller;

import GreenPrint.api.domain.imagem_produto.DadosImagemProduto;
import GreenPrint.api.domain.imagem_produto.ImagemProduto;
import GreenPrint.api.domain.imagem_produto.ImagemProdutoRepository;
import GreenPrint.api.domain.preco_produto.PrecoProduto;
import GreenPrint.api.domain.preco_produto.PrecoProdutoRepository;
import GreenPrint.api.domain.produto.*;
import GreenPrint.api.domain.tipo_papelao.TipoPapelao;
import GreenPrint.api.domain.tipo_papelao.TipoPapelaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoPapelaoRepository tipoPapelaoRepository;

    @Autowired
    private PrecoProdutoRepository precoProdutoRepository;

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriComponentsBuilder){
        // Busca a entidade TipoPapelao pelo ID do DTO
        TipoPapelao tipo = tipoPapelaoRepository.findById(dados.idTipoPapelao())
                .orElseThrow(() -> new RuntimeException("Tipo de papelão não encontrado"));

        // Cria o Produto usando o construtor que recebe DTO + TipoPapelao
        Produto produto = new Produto(dados, tipo);

        produtoRepository.save(produto);

        if (dados.valorCompra() != null && dados.valorVenda() != null) {
            PrecoProduto preco = new PrecoProduto(produto, dados.valorCompra(), dados.valorVenda());
            precoProdutoRepository.save(preco);
        }

        // Salvar imagem
        if (dados.imagens() != null) {
            for (DadosImagemProduto img : dados.imagens()) {
                ImagemProduto imagem = new ImagemProduto(produto, img.arquivoImagem(), img.tipoImagem());
                imagemProdutoRepository.save(imagem);
            }
        }

        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getIdProduto()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(size = 30, sort = {"nome"}) Pageable pageable){
        var page = produtoRepository.findAll(pageable).map(DadosListagemProduto::new);
        return  ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados){
        var produto = produtoRepository.getReferenceById(dados.id());

        if (dados.idTipoPapelao() != null) {
            TipoPapelao tipo = tipoPapelaoRepository.findById(dados.idTipoPapelao())
                    .orElseThrow(() -> new RuntimeException("Tipo de papelão não encontrado"));
            produto.setTipoPapelao(tipo);
        }

        produto.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
      produtoRepository.deleteById(id);
      return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){

        var produto = produtoRepository.getReferenceById(id);

        return  ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }
}
