package br.com.produtoapi.produtoapi.modules.produto.dto;

import br.com.produtoapi.produtoapi.modules.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProdutoResponse {

    private Integer idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Double precoProduto;
    private Integer idFornecedor;
    private String razaoSocialFornecedor;
    private String cnpjFornecedor;
    private Integer idCategoria;
    private String descricaoCategoria;

    public static ProdutoResponse of(Produto produto) {
        return ProdutoResponse
            .builder()
            .idProduto(produto.getId())
            .nomeProduto(produto.getNome())
            .descricaoProduto(produto.getDescricao())
            .precoProduto(produto.getPreco())
            .idFornecedor(produto.getFornecedor().getId())
            .razaoSocialFornecedor(produto.getFornecedor().getRazaoSocial())
            .cnpjFornecedor(produto.getFornecedor().getCnpj())
            .idCategoria(produto.getCategoria().getId())
            .descricaoCategoria(produto.getCategoria().getDescricao())
            .build();
    }
}
