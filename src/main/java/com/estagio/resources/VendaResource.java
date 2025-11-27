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
    public ResponseEntity<List<VendaDTO>> findAll() {
        List<VendaDTO> vendas = vendaService.findAll()
                .stream()
                .map(VendaDTO::new)
                .toList();

        return ResponseEntity.ok(vendas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Long id) {
        Venda venda = vendaService.findById(id);
        return ResponseEntity.ok(new VendaDTO(venda));
    }


    @PostMapping
    public ResponseEntity<VendaDTO> criarVenda(@RequestBody VendaDTO dto) {
        Venda venda = vendaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new VendaDTO(venda));
    }



}

