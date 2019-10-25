package br.com.vendaapi.vendaapi.modules.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarrinhoRequest {

    private Integer produtoId;
    private Integer quantidade;

}
