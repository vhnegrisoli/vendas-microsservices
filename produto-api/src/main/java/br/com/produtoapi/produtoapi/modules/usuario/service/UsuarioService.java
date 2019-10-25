package br.com.produtoapi.produtoapi.modules.usuario.service;

import br.com.produtoapi.produtoapi.modules.usuario.client.UsuarioClient;
import br.com.produtoapi.produtoapi.modules.usuario.dto.UsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioClient usuarioClient;

    public UsuarioAutenticado getUsuarioAutenticado() {
        return usuarioClient.getUsuarioAutenticado();
    }
}
