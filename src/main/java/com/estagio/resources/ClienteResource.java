package com.estagio.resources;

import com.estagio.domains.Cliente;
import com.estagio.domains.dtos.ClienteDTO;
import com.estagio.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping //exemplo: localhost:8080/cliente
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        Cliente obj = this.clienteService.findbyId(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf){
        Cliente obj = this.clienteService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ClienteDTO> findByEmail(@PathVariable String email){
        Cliente obj = this.clienteService.findbyEmail(email);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto){
        Cliente cliente = clienteService.create(dto);
        //Cria a URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        //Retorna a resposta com o status 201 Created e o local do recurso criado
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO objDto){
        Cliente Obj = clienteService.update(id,objDto);
        return ResponseEntity.ok().body(new ClienteDTO(Obj));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
