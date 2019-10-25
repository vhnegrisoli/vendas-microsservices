package br.com.vendaapi.vendaapi.modules.produto.dto;

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

}
