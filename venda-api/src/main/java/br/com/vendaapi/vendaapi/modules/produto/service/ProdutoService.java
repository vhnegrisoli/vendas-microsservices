package br.com.vendaapi.vendaapi.modules.produto.service;

import br.com.vendaapi.vendaapi.modules.produto.client.ProdutoClient;
import br.com.vendaapi.vendaapi.modules.produto.dto.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoClient produtoClient;

    public List<ProdutoResponse> findProdutosByIds(List<Integer> produtosIds) {
        return produtoClient.findProdutosByIds(produtosIds);
    }
}
