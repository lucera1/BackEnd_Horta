package com.estagio.resources;

import com.estagio.domains.Venda;
import com.estagio.domains.dtos.VendaDTO;
import com.estagio.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<Venda>> findAll() {
        List<Venda> vendas = vendaService.findAll();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id) {
        Venda venda = vendaService.findById(id);
        return ResponseEntity.ok(venda);
    }

    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody VendaDTO dto) {
        Venda venda = vendaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }
}

