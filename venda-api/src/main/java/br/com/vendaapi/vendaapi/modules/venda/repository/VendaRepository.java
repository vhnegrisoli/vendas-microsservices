package br.com.vendaapi.vendaapi.modules.venda.repository;

import br.com.vendaapi.vendaapi.modules.venda.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    Optional<Venda> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    List<Venda> findByUsuarioId(Integer usuarioId);
}
