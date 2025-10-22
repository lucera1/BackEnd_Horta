package com.estagio.services;
import com.estagio.domains.Cliente;
import com.estagio.domains.Pedido;
import com.estagio.domains.Venda;
import com.estagio.domains.dtos.PedidoDTO;
import com.estagio.domains.enums.FormaPagamento;
import com.estagio.domains.enums.StatusPedido;
import com.estagio.domains.produtos.Produto;
import com.estagio.domains.state.Cancelado;
import com.estagio.repositories.PedidoRepository;
import com.estagio.repositories.ProdutoRepository;
import com.estagio.repositories.VendaRepository;
import com.estagio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private VendaRepository vendaRepo;

    public Pedido findbyId(Long id){
        Optional<Pedido> obj = pedidoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:"+id));
    }

    public List<PedidoDTO> findAll(){
        return pedidoRepo.findAll().stream()
                .map(obj -> new PedidoDTO(obj)).collect(Collectors.toList());
    }

    private Pedido newsPedido(PedidoDTO obj){
        Cliente cliente = clienteService.findbyId(obj.getCliente());

        Pedido pedido = new Pedido();
        if(obj.getId() != null){
            pedido.setId(obj.getId());
        }

        pedido.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setFormaPagamento(FormaPagamento.valueOf(obj.getFormaPagamento().toUpperCase()));


        return pedido;
    }

    public Pedido create (PedidoDTO objDto){
        return pedidoRepo.save(newsPedido(objDto));
    }

    public Pedido update (Long id, PedidoDTO objDto){
        objDto.setId(id);
        Pedido oldObj;
        oldObj = newsPedido(objDto);
        return pedidoRepo.save(oldObj);
    }

    public void delete(Long id){
        Pedido obj = findbyId(id);
        pedidoRepo.deleteById(id);
    }

    public Pedido preparar(Long id) {
        Pedido pedido = getPedido(id);
        pedido.preparar(); // delega ao estado atual
        return pedidoRepo.save(pedido);
    }

    public Pedido entregar(Long id) {
        Pedido pedido = getPedido(id);
        pedido.entregar();
        return pedidoRepo.save(pedido);
    }

    public Pedido cancelar(Long id) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new RuntimeException("Pedido já está cancelado");
        }

        // Devolve os produtos ao estoque e exclui as vendas
        for (Venda venda : new ArrayList<>(pedido.getVendas())) {
            Produto produto = venda.getProduto();
            produto.setQtdEstoque(produto.getQtdEstoque() + venda.getQuantidade());
            produtoRepo.save(produto);

            vendaRepo.delete(venda);
        }

        pedido.getVendas().clear();
        pedido.setValorTotal(BigDecimal.ZERO);
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setEstadoPedido(new Cancelado()); // se estiver usando padrão State
        pedidoRepo.save(pedido);
        return pedido;
    }

    public Pedido getPedido(Long id) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado com id: " + id));
        pedido.atualizarEstadoPedido();  // <-- ESSA LINHA IMPORTA!
        return pedido;
    }

}



