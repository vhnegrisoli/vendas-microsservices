package br.com.vendaapi.vendaapi.modules.usuario.client;

import br.com.vendaapi.vendaapi.modules.usuario.dto.UsuarioAutenticado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    contextId = "usuarioClient",
    name = "usuarioAutenticadoClient",
    url = "${app-config.services.usuario-api.url}")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/usuario-autenticado")
    UsuarioAutenticado getUsuarioAutenticado();

}