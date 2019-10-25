package br.com.vendaapi.vendaapi.modules.venda.repository;

import br.com.vendaapi.vendaapi.modules.venda.model.CarrinhoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompra, Integer> {

    List<CarrinhoCompra> findByVendaId(Integer vendaId);

    List<CarrinhoCompra> findByVendaIdIn(List<Integer> vendaId);

}
