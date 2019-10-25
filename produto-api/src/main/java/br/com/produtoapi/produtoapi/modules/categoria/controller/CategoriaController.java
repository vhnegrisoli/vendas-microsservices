package br.com.produtoapi.produtoapi.modules.categoria.controller;

import br.com.produtoapi.produtoapi.modules.categoria.model.Categoria;
import br.com.produtoapi.produtoapi.modules.categoria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> buscarTodas() {
        return categoriaService.buscarTodas();
    }

    @GetMapping("{id}")
    public Categoria buscarUma(@PathVariable Integer id) {
        return categoriaService.buscarUma(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Categoria criada com sucesso!")
    public void save(@RequestBody Categoria categoria) {
        categoriaService.save(categoria);
    }
}
