package com.estagio.services;

import com.estagio.domains.Cliente;
import com.estagio.domains.Pedido;
import com.estagio.domains.Venda;
import com.estagio.domains.enums.FormaPagamento;
import com.estagio.domains.enums.TipoProduto;
import com.estagio.domains.enums.TipoUsuario;
import com.estagio.domains.produtos.GrupoProduto;
import com.estagio.domains.produtos.Produto;
import com.estagio.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private GrupoProdutoRepository grupoRepo;

    @Autowired
    private VendaRepository vendaRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void initDB() {

        //cria grupo produto
        TipoProduto verdura = TipoProduto.VERDURA;
        GrupoProduto grupoProduto1 = new GrupoProduto(verdura.getId(), verdura.getTipoProduto());
        grupoProduto1 = grupoRepo.save(grupoProduto1);

        TipoProduto legume = TipoProduto.LEGUME;
        GrupoProduto grupoProduto2 = new GrupoProduto(legume.getId(), legume.getTipoProduto());
        grupoProduto2 = grupoRepo.save(grupoProduto2);


        //cria produto
        Produto produto1 = new Produto(null, "ALFACE", new BigDecimal(15.00), 100, grupoProduto1,
                "https://www.sitiodamata.com.br/media/catalog/product/cache/bd7e41e8357e6ae06c7d33758afd4978/a/l/alface-crocantela-organica-lactuca-sativa-l_2nd.jpg");
        produto1 = produtoRepo.save(produto1);

        Produto produto2 = new Produto(null, "BATATA", new BigDecimal(10.00), 100, grupoProduto2,
                "https://ceagesp.gov.br/wp-content/uploads/2016/10/batataportal-600x469.jpg");
        produto2 = produtoRepo.save(produto2);

        Produto produto3 = new Produto(null, "BETERRABA", new BigDecimal(15.00), 100, grupoProduto2,
                "https://www.infoescola.com/wp-content/uploads/2010/02/beterraba_675904528.jpg");
        produto3 = produtoRepo.save(produto3);



        Produto produto4 = new Produto(null, "COUVE", new BigDecimal(12.00), 80, grupoProduto1,
                "https://images.tcdn.com.br/img/img_prod/763396/couve_manteiga_169_1_20200320135333.jpg");
        produto4 = produtoRepo.save(produto4);

        Produto produto5 = new Produto(null, "ESPINAFRE", new BigDecimal(14.00), 70, grupoProduto1,
                "https://hortifrutibr.vtexassets.com/arquivos/ids/173610/Espinafre.jpg.jpg?v=638888917235370000");
        produto5 = produtoRepo.save(produto5);

        Produto produto6 = new Produto(null, "RÚCULA", new BigDecimal(11.00), 90, grupoProduto1,
                "https://www.hortifruti.com.br/_next/image?url=https%3A%2F%2Fhortifrutibr.vtexassets.com%2Farquivos%2Fids%2F175429%2Fd18168c0153a7145243953c3c02916cdeeae3e-Photoroom.jpg%3Fv%3D638888912908130000%26format%3Dwebp&w=1440&q=75");
        produto6 = produtoRepo.save(produto6);



        Produto produto7 = new Produto(null, "CENOURA", new BigDecimal(12.00), 150, grupoProduto2,
                "https://www.infoescola.com/wp-content/uploads/2010/08/cenoura_250834906.jpg");
        produto7 = produtoRepo.save(produto7);

        Produto produto8 = new Produto(null, "TOMATE", new BigDecimal(18.00), 120, grupoProduto2,
                "https://webrun.com.br/wp-content/uploads/2024/02/AdobeStock_69282769.jpeg");
        produto8 = produtoRepo.save(produto8);

        Produto produto9 = new Produto(null, "ABOBRINHA", new BigDecimal(9.00), 100, grupoProduto2,
                "https://urbanfarmipiranga.com.br/wp-content/uploads/2022/09/7309.png");
        produto9 = produtoRepo.save(produto9);

        Produto produto10 = new Produto(null, "BERINJELA", new BigDecimal(16.00), 110, grupoProduto2,
                "https://imagens.valesaude.com.br/images/beneficios-da-berinjela-conheca-as-vantagens-no-consumo-do-legume.webp");
        produto10 = produtoRepo.save(produto10);


        // cria cliente e salva
        Cliente cliente1 = new Cliente(null,"Guilherme", "Lucera", "000.000.000-00",
                "gui@gmail.com", encoder.encode("123"), TipoUsuario.CLIENTE);
        cliente1 = clienteRepo.save(cliente1);

        Cliente cliente2 = new Cliente(null, "João", "Silva", "111.111.111-11",
                "joao@gmail.com", encoder.encode("1234"), TipoUsuario.CLIENTE);
        cliente2 = clienteRepo.save(cliente2);


        //cria pedido
        Pedido pedido1 = new Pedido(null, cliente1, LocalDate.now(), FormaPagamento.DINHEIRO);
        pedido1 = pedidoRepo.save(pedido1);

        Pedido pedido2 = new Pedido(null, cliente2, LocalDate.now(), FormaPagamento.DINHEIRO);
        pedido2 = pedidoRepo.save(pedido2);

        //cria venda
        Venda venda1 = new Venda(null, cliente1, produto2, 10);
        venda1.setPedido(pedido1);
        venda1 = vendaRepo.save(venda1);

        Venda venda2 = new Venda(null, cliente2, produto1, 3);
        venda2.setPedido(pedido2);
        venda2 = vendaRepo.save(venda2);

        Venda venda3 = new Venda(null, cliente1, produto3, 2);
        venda3.setPedido(pedido1);
        venda3 = vendaRepo.save(venda3);

        Venda venda4 = new Venda(null, cliente2, produto1, 1);
        venda4.setPedido(pedido2);
        venda4 = vendaRepo.save(venda4);





        pedido1.setVendas(new ArrayList<>(List.of(venda1, venda3))); // ou o que for apropriado
        pedido1.setValorTotal(pedido1.calcularValorTotal());
        pedidoRepo.save(pedido1);



        pedido2.setVendas(new ArrayList<>(List.of(venda2, venda4))); // ou o que for apropriado
        pedido2.setValorTotal(pedido2.calcularValorTotal());
        pedidoRepo.save(pedido2);


    }


}
