package br.com.vendaapi.vendaapi.modules.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VendaRequest {

    private Integer id;
    private String clienteNome;
    private String clienteEmail;
    private String clienteCpf;
    private List<CarrinhoRequest> carrinhoRequests;

}
