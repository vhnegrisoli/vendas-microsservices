package br.com.produtoapi.produtoapi.modules.usuario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioAutenticado {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String permissao;
    private String descricao;
    private LocalDateTime ultimoAcesso;

}
