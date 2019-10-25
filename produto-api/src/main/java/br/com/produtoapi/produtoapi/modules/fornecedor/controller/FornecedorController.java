package br.com.produtoapi.produtoapi.modules.fornecedor.controller;

import br.com.produtoapi.produtoapi.modules.fornecedor.model.Fornecedor;
import br.com.produtoapi.produtoapi.modules.fornecedor.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> buscarTodos() {
        return fornecedorService.buscarTodos();
    }

    @GetMapping("{id}")
    public Fornecedor buscarUm(@PathVariable Integer id) {
        return fornecedorService.buscarUm(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Fornecedor criado com sucesso!")
    public void save(@RequestBody Fornecedor fornecedor) {
        fornecedorService.save(fornecedor);
    }
}