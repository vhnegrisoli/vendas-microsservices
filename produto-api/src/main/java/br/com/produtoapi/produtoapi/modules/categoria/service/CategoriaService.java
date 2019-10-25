package br.com.produtoapi.produtoapi.modules.categoria.service;

import br.com.produtoapi.produtoapi.config.exception.ValidacaoException;
import br.com.produtoapi.produtoapi.modules.categoria.model.Categoria;
import br.com.produtoapi.produtoapi.modules.categoria.repository.CategoriaRepository;
import br.com.produtoapi.produtoapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Categoria> buscarTodas() {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return categoriaRepository.findByUsuarioId(usuarioAutenticado.getId());
    }

    public Categoria buscarUma(Integer id) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return categoriaRepository.findByIdAndUsuarioId(id, usuarioAutenticado.getId())
            .orElseThrow(() -> new ValidacaoException("A categoria não foi encontrada."));
    }

    public void save(Categoria categoria) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        categoria.setUsuarioId(usuarioAutenticado.getId());
        validarCategoriaExistente(categoria);
        categoriaRepository.save(categoria);
    }

    private void validarCategoriaExistente(Categoria categoria) {
        categoriaRepository
            .findByIdAndUsuarioId(categoria.getId(), categoria.getUsuarioId())
            .ifPresent(categoriaExistente -> {
                if (!categoria.isNovoCadastro() && !categoria.getId().equals(categoriaExistente.getId())) {
                    throw new ValidacaoException("A categoria " + categoria.getDescricao() + " já existe.");
                }
            });
    }
}
