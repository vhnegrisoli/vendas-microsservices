package br.com.produtoapi.produtoapi.modules.categoria.repository;

import br.com.produtoapi.produtoapi.modules.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    List<Categoria> findByUsuarioId(Integer usuarioId);
}
