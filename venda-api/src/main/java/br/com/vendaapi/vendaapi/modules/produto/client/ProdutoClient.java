package br.com.vendaapi.vendaapi.modules.produto.client;

import br.com.vendaapi.vendaapi.modules.produto.dto.ProdutoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    contextId = "produtoClient",
    name = "produtoClient",
    url = "${app-config.services.produto-api.url}")
public interface ProdutoClient {

    @GetMapping("/api/produtos/especificos")
    List<ProdutoResponse> findProdutosByIds(@RequestParam("ids") List<Integer> produtosIds);

}
