package com.estagio.resources;

import com.estagio.domains.dtos.GrupoProdutoDTO;
import com.estagio.domains.produtos.GrupoProduto;
import com.estagio.services.GrupoProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/grupoproduto")
public class GrupoProdutoResource {

    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @GetMapping //exemplo: http//localhost:8080/grupoproduto
    public ResponseEntity<List<GrupoProdutoDTO>> findAll(){
        return ResponseEntity.ok().body(grupoProdutoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GrupoProdutoDTO> findById(@PathVariable Long id){
        GrupoProduto obj = this.grupoProdutoService.findbyId(id);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GrupoProdutoDTO> delete(@PathVariable Long id){
        grupoProdutoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
