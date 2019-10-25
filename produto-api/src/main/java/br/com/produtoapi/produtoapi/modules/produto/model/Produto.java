package br.com.produtoapi.produtoapi.modules.produto.model;

import br.com.produtoapi.produtoapi.modules.categoria.model.Categoria;
import br.com.produtoapi.produtoapi.modules.fornecedor.model.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", length = 120)
    @NotNull
    private String nome;

    @Column(name = "DESCRICAO", length = 120)
    @NotNull
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "FK_FORNECEDOR")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORIA")
    private Categoria categoria;

    @Column(name = "USUARIO_ID")
    @NotNull
    private Integer usuarioId;
}
