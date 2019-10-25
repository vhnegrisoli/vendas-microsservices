package br.com.vendaapi.vendaapi.modules.usuario.service;

import br.com.vendaapi.vendaapi.modules.usuario.client.UsuarioClient;
import br.com.vendaapi.vendaapi.modules.usuario.dto.UsuarioAutenticado;
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
