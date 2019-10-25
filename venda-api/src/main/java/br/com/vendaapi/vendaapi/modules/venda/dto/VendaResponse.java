package br.com.vendaapi.vendaapi.modules.venda.dto;

import br.com.vendaapi.vendaapi.modules.produto.dto.ProdutoResponse;
import br.com.vendaapi.vendaapi.modules.venda.enums.EVendaSituacao;
import br.com.vendaapi.vendaapi.modules.venda.model.Venda;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VendaResponse {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCompra;
    private String usuarioId;
    private Integer usuarioNome;
    private Integer usuarioEmail;
    private EVendaSituacao situacao;
    private String clienteNome;
    private String clienteEmail;
    private String clienteCpf;
    private List<ProdutoResponse> produtosVenda;

    public static VendaResponse of(Venda venda, List<ProdutoResponse> produtosVenda) {
        var response = new VendaResponse();
        BeanUtils.copyProperties(venda, response);
        return response;
    }
}
