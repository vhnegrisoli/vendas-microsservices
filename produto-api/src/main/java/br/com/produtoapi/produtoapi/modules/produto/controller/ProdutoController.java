package br.com.produtoapi.produtoapi.modules.produto.controller;

import br.com.produtoapi.produtoapi.modules.produto.dto.ProdutoResponse;
import br.com.produtoapi.produtoapi.modules.produto.model.Produto;
import br.com.produtoapi.produtoapi.modules.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("especificos")
    public List<ProdutoResponse> buscarProdutosPorIds(@RequestParam("ids") List<Integer> ids) {
        return produtoService.buscarPordutosPorIds(ids);
    }

    @GetMapping("{id}")
    public ProdutoResponse buscarUm(@PathVariable Integer id) {
        return produtoService.buscarUm(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Produto criado com sucesso!")
    public void save(@RequestBody Produto produto) {
        produtoService.save(produto);
    }
}