package br.com.produtoapi.produtoapi.modules.produto.repository;

import br.com.produtoapi.produtoapi.modules.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
