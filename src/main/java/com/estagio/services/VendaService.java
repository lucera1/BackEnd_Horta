package com.estagio.services;

import com.estagio.domains.Cliente;
import com.estagio.domains.Pedido;
import com.estagio.domains.Venda;
import com.estagio.domains.dtos.VendaDTO;
import com.estagio.domains.enums.FormaPagamento;
import com.estagio.domains.produtos.Produto;
import com.estagio.repositories.ClienteRepository;
import com.estagio.repositories.PedidoRepository;
import com.estagio.repositories.ProdutoRepository;
import com.estagio.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    public List<Venda> findAll() {
        return vendaRepo.findAll();
    }

    public Venda findById(Long id) {
        return vendaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com id: " + id));
    }

    public Venda create(VendaDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Produto produto = produtoRepo.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.getPedidoId() == null) {
            throw new RuntimeException("Pedido é obrigatório para criar uma venda");
        }

        Pedido pedido = pedidoRepo.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Verifica se estoque é suficiente antes de criar a venda
        if (produto.getQtdEstoque() < dto.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        Venda venda = new Venda(null, cliente, produto, dto.getQuantidade());
        venda.setPedido(pedido);
        venda = vendaRepo.save(venda);

        // Atualiza o estoque do produto
        produto.setQtdEstoque(produto.getQtdEstoque() - dto.getQuantidade());
        produtoRepo.save(produto);

        pedido.getVendas().add(venda);
        pedido.setValorTotal(pedido.calcularValorTotal());
        pedidoRepo.save(pedido);

        return venda;
    }
}
