package br.com.produtoapi.produtoapi.modules.produto.service;

import br.com.produtoapi.produtoapi.config.exception.ValidacaoException;
import br.com.produtoapi.produtoapi.modules.produto.dto.ProdutoResponse;
import br.com.produtoapi.produtoapi.modules.produto.model.Produto;
import br.com.produtoapi.produtoapi.modules.produto.repository.ProdutoRepository;
import br.com.produtoapi.produtoapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Produto> buscarTodos() {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return produtoRepository.findByUsuarioId(usuarioAutenticado.getId());
    }

    public ProdutoResponse buscarUm(Integer id) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return ProdutoResponse.of(produtoRepository
            .findByIdAndUsuarioId(id, usuarioAutenticado.getId())
            .orElseThrow(() -> new ValidacaoException("O produto não foi encontrado.")));
    }

    public void save(Produto produto) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        produto.setUsuarioId(usuarioAutenticado.getId());
        validarProdutoExistente(produto);
        produtoRepository.save(produto);
    }

    private void validarProdutoExistente(Produto produto) {
        produtoRepository
            .findByIdAndUsuarioId(produto.getId(), produto.getUsuarioId())
            .ifPresent(produtoExistente -> {
                if (!produto.isNovoCadastro() && !produto.getId().equals(produtoExistente.getId())) {
                    throw new ValidacaoException("O produto " + produto.getNome() + " já existe.");
                }
            });
    }

    public List<ProdutoResponse> buscarPordutosPorIds(List<Integer> ids) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return produtoRepository.findByUsuarioIdAndIdIn(usuarioAutenticado.getId(), ids)
            .stream()
            .map(ProdutoResponse::of)
            .collect(Collectors.toList());
    }
}