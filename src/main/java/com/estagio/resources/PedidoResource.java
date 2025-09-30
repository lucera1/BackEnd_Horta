package com.estagio.resources;

import com.estagio.domains.Pedido;
import com.estagio.domains.dtos.GrupoProdutoDTO;
import com.estagio.domains.dtos.PedidoDTO;
import com.estagio.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> findbyId(@PathVariable Long id){
        Pedido obj = this.pedidoService.findbyId(id);
        return ResponseEntity.ok().body(new PedidoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        return ResponseEntity.ok().body(pedidoService.findAll());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO objDto){
        Pedido newObj = pedidoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")

    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto){
        Pedido Obj = pedidoService.update(id, objDto);
        return ResponseEntity.ok().body(new PedidoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> delete(@PathVariable Long id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/preparar")
    public ResponseEntity<Pedido> preparar(@PathVariable Long id) {
        Pedido pedido = pedidoService.preparar(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/entregar")
    public ResponseEntity<Pedido> entregar(@PathVariable Long id) {
        Pedido pedido = pedidoService.entregar(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelar(@PathVariable Long id) {
        Pedido pedido = pedidoService.cancelar(id);
        return ResponseEntity.ok(pedido);
    }
}
