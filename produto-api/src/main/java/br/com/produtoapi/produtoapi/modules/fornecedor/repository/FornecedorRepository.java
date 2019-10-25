package br.com.produtoapi.produtoapi.modules.fornecedor.repository;

import br.com.produtoapi.produtoapi.modules.fornecedor.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Optional<Fornecedor> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    List<Fornecedor> findByUsuarioId(Integer usuarioId);

}
