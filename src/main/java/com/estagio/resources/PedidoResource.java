package com.estagio.resources;

import com.estagio.domains.Pedido;
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
        return ResponseEntity.ok(new PedidoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO objDto) {
        Pedido newObj = pedidoService.create(objDto);
        PedidoDTO dto = new PedidoDTO(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto){
        Pedido Obj = pedidoService.update(id, objDto);
        return ResponseEntity.ok(new PedidoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/preparar")
    public ResponseEntity<PedidoDTO> preparar(@PathVariable Long id) {
        Pedido pedido = pedidoService.preparar(id);
        return ResponseEntity.ok(new PedidoDTO(pedido));
    }

    @PutMapping("/{id}/entregar")
    public ResponseEntity<PedidoDTO> entregar(@PathVariable Long id) {
        Pedido pedido = pedidoService.entregar(id);
        return ResponseEntity.ok(new PedidoDTO(pedido));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PedidoDTO> cancelar(@PathVariable Long id) {
        Pedido pedido = pedidoService.cancelar(id);
        return ResponseEntity.ok(new PedidoDTO(pedido));
    }
}
