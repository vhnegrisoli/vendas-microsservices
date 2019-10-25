package br.com.usuarioapi.usuarioapi.modules.usuario.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    contextId = "usuarioClient",
    name = "usuarioAutenticadoClient",
    url = "${app-config.services.produto-api.url}")
public interface UsuarioClient {

}
