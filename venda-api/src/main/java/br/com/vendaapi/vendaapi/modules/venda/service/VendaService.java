package br.com.vendaapi.vendaapi.modules.venda.service;

import br.com.vendaapi.vendaapi.config.exception.ValidacaoException;
import br.com.vendaapi.vendaapi.modules.produto.service.ProdutoService;
import br.com.vendaapi.vendaapi.modules.usuario.service.UsuarioService;
import br.com.vendaapi.vendaapi.modules.venda.dto.CarrinhoRequest;
import br.com.vendaapi.vendaapi.modules.venda.dto.VendaRequest;
import br.com.vendaapi.vendaapi.modules.venda.dto.VendaResponse;
import br.com.vendaapi.vendaapi.modules.venda.model.CarrinhoCompra;
import br.com.vendaapi.vendaapi.modules.venda.model.Venda;
import br.com.vendaapi.vendaapi.modules.venda.repository.CarrinhoCompraRepository;
import br.com.vendaapi.vendaapi.modules.venda.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.vendaapi.vendaapi.modules.venda.dto.VendaResponse.of;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private CarrinhoCompraRepository carrinhoCompraRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public VendaResponse saveVenda(VendaRequest request) {
        var venda = Venda.of(request, usuarioService.getUsuarioAutenticado());
        vendaRepository.save(venda);
        var carrinhoCompra = salvarProdutosDoCarrinhoDeCompra(venda, request.getCarrinhoRequests());
        return of(venda, produtoService.findProdutosByIds(getIdsDosProdutos(carrinhoCompra)));
    }

    private List<CarrinhoCompra> salvarProdutosDoCarrinhoDeCompra(Venda venda, List<CarrinhoRequest> request) {
        var carrinhoCompra = request
            .stream()
            .map(CarrinhoCompra::of)
            .collect(Collectors.toList());
        carrinhoCompra.forEach(
            item -> item.setVenda(venda)
        );
        return carrinhoCompraRepository.saveAll(carrinhoCompra);
    }

    private List<Integer> getIdsDosProdutos(List<CarrinhoCompra> carrinhoCompra) {
        return carrinhoCompra
            .stream()
            .map(CarrinhoCompra::getProdutoId)
            .collect(Collectors.toList());
    }

    public VendaResponse buscarUma(Integer id) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        var venda = vendaRepository.findByIdAndUsuarioId(id, usuarioAutenticado.getId())
            .orElseThrow(() -> new ValidacaoException("Venda não encontrada."));
        var carrinhoCompra = carrinhoCompraRepository.findByVendaId(venda.getId());
        return of(venda, produtoService.findProdutosByIds(getIdsDosProdutos(carrinhoCompra)));
    }

    public List<VendaResponse> buscarTodas() {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        var vendas = vendaRepository.findByUsuarioId(usuarioAutenticado.getId());
        ArrayList<VendaResponse> response = new ArrayList<>();
        vendas
            .forEach(venda -> {
                var carrinhoCompra = carrinhoCompraRepository.findByVendaId(venda.getId());
                response.add(of(venda, produtoService.findProdutosByIds(getIdsDosProdutos(carrinhoCompra))));
            });
        return response;
    }
}