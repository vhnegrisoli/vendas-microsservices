package br.com.produtoapi.produtoapi.modules.fornecedor.service;

import br.com.produtoapi.produtoapi.config.exception.ValidacaoException;
import br.com.produtoapi.produtoapi.modules.fornecedor.model.Fornecedor;
import br.com.produtoapi.produtoapi.modules.fornecedor.repository.FornecedorRepository;
import br.com.produtoapi.produtoapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Fornecedor> buscarTodos() {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return fornecedorRepository.findByUsuarioId(usuarioAutenticado.getId());
    }

    public Fornecedor buscarUm(Integer id) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        return fornecedorRepository.findByIdAndUsuarioId(id, usuarioAutenticado.getId())
            .orElseThrow(() -> new ValidacaoException("O fornecedor não foi encontrado."));
    }

    public void save(Fornecedor fornecedor) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        fornecedor.setUsuarioId(usuarioAutenticado.getId());
        validarFornecedorExistente(fornecedor);
        fornecedorRepository.save(fornecedor);
    }

    private void validarFornecedorExistente(Fornecedor fornecedor) {
        fornecedorRepository
            .findByIdAndUsuarioId(fornecedor.getId(), fornecedor.getUsuarioId())
            .ifPresent(fornecedorExistente -> {
                if (!fornecedor.isNovoCadastro() && !fornecedor.getId().equals(fornecedorExistente.getId())) {
                    throw new ValidacaoException("O fornecedor " + fornecedor.getRazaoSocial() + " já existe.");
                }
            });
    }
}