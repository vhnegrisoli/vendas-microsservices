package br.com.produtoapi.produtoapi.modules.fornecedor.model;

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
@Table(name = "FORNECEDOR")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "RAZAO_SOCIAL", length = 120)
    @NotNull
    private String razaoSocial;

    @Column(name = "CNPJ", length = 20)
    @NotNull
    private String cnpj;

    @Column(name = "USUARIO_ID")
    @NotNull
    private Integer usuarioId;

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }
}
