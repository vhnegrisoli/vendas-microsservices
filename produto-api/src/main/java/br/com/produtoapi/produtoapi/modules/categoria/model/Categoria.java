package br.com.produtoapi.produtoapi.modules.categoria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRICAO", length = 120)
    @NotNull
    private String descricao;

    @Column(name = "USUARIO_ID")
    @NotNull
    private Integer usuarioId;

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }
}
