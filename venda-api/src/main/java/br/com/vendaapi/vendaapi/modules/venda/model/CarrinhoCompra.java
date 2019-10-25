package br.com.vendaapi.vendaapi.modules.venda.model;

import br.com.vendaapi.vendaapi.modules.venda.dto.CarrinhoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "CARRINHO_COMPRA")
public class CarrinhoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "PRODUTO_ID")
    @NotNull
    private Integer produtoId;

    @ManyToOne
    @JoinColumn(name = "FK_VENDA")
    @NotNull
    private Venda venda;

    @Column(name = "QUANTIDADE")
    @NotNull
    private Integer quantidade;

    public static CarrinhoCompra of(CarrinhoRequest request) {
        var carrinhoCompra = new CarrinhoCompra();
        BeanUtils.copyProperties(request, carrinhoCompra);
        return carrinhoCompra;
    }
}
