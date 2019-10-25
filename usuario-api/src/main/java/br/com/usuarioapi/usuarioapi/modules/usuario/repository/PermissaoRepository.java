package br.com.usuarioapi.usuarioapi.modules.usuario.repository;

import br.com.usuarioapi.usuarioapi.modules.usuario.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
}
