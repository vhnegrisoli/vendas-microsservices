package br.com.produtoapi.produtoapi.config.auth;

import lombok.Getter;

public enum EPermissao {

    ADMIN("Administrador"),
    USER("Usuário");

    @Getter
    private String descricao;

    EPermissao(String descricao) {
        this.descricao = descricao;
    }
}
