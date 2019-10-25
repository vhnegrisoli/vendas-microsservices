package br.com.vendaapi.vendaapi.modules.venda.model;

import br.com.vendaapi.vendaapi.modules.usuario.dto.UsuarioAutenticado;
import br.com.vendaapi.vendaapi.modules.venda.enums.EVendaSituacao;
import br.com.vendaapi.vendaapi.modules.venda.dto.VendaRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static br.com.vendaapi.vendaapi.modules.venda.enums.EVendaSituacao.ABERTA;
import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "VENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DATA_COMPRA")
    @NotNull
    private LocalDateTime dataCompra;

    @Column(name = "USUARIO_ID")
    @NotNull
    private Integer usuarioId;

    @Column(name = "USUARIO_NOME")
    @NotNull
    private String usuarioNome;

    @Column(name = "USUARIO_EMAIL")
    @NotNull
    private String usuarioEmail;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EVendaSituacao situacao;

    @Column(name = "CLIENTE_NOME")
    @NotNull
    private String clienteNome;

    @Column(name = "CLIENTE_EMAIL")
    @NotNull
    private String clienteEmail;

    @Column(name = "CLIENTE_CPF")
    @NotNull
    @CPF
    private String clienteCpf;

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }

    public static Venda of(VendaRequest request, UsuarioAutenticado usuarioAutenticado) {
        var venda = new Venda();
        BeanUtils.copyProperties(request, venda);
        venda.setDataCompra(LocalDateTime.now());
        venda.setUsuarioId(usuarioAutenticado.getId());
        venda.setUsuarioNome(usuarioAutenticado.getNome());
        venda.setUsuarioEmail(usuarioAutenticado.getEmail());
        venda.setSituacao(ABERTA);
        return venda;
    }
}
