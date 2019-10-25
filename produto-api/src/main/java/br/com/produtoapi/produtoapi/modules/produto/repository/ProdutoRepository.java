package br.com.produtoapi.produtoapi.modules.produto.repository;

import br.com.produtoapi.produtoapi.modules.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    List<Produto> findByUsuarioId(Integer usuarioId);

    List<Produto> findByUsuarioIdAndIdIn(Integer usuarioId, List<Integer> ids);
}
