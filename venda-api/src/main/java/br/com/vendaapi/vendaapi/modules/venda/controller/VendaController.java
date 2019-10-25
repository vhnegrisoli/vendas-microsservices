package br.com.vendaapi.vendaapi.modules.venda.controller;

import br.com.vendaapi.vendaapi.modules.venda.dto.VendaRequest;
import br.com.vendaapi.vendaapi.modules.venda.dto.VendaResponse;
import br.com.vendaapi.vendaapi.modules.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<VendaResponse> buscarTodas() {
        return vendaService.buscarTodas();
    }

    @GetMapping("{id}")
    public VendaResponse buscarUma(@PathVariable Integer id) {
        return vendaService.buscarUma(id);
    }

    @PostMapping
    public VendaResponse salvar(@RequestBody VendaRequest vendaRequest) {
        return vendaService.saveVenda(vendaRequest);
    }
}