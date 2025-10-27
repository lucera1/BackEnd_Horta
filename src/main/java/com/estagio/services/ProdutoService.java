package com.estagio.services;

import com.estagio.domains.dtos.ProdutoDTO;
import com.estagio.domains.produtos.GrupoProduto;
import com.estagio.domains.produtos.Produto;
import com.estagio.repositories.GrupoProdutoRepository;
import com.estagio.repositories.ProdutoRepository;
import com.estagio.services.exceptions.DataIntegrityViolationException;
import com.estagio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private GrupoProdutoRepository grupoProdutoRepo;

    public List<ProdutoDTO> findAll() {
        return produtoRepo.findAll().stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
    }

    public Produto findbyId(Long id) {
        Optional<Produto> obj = produtoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: " + id));
    }

    public Produto create(ProdutoDTO dto) {
        dto.setId(null);

        GrupoProduto grupoProduto = grupoProdutoRepo.findById(dto.getGrupoProduto())
                .orElseThrow(() -> new DataIntegrityViolationException(
                        "Grupo de Produto -" + dto.getGrupoProduto() + " não está cadastrado!"));

        Produto obj = new Produto();
        copyDtoToEntity(dto, obj);
        obj.setGrupoProduto(grupoProduto);

        return produtoRepo.save(obj);
    }

    public Produto update(Long id, ProdutoDTO dto) {
        Produto entity = findbyId(id);
        copyDtoToEntity(dto, entity);

        GrupoProduto grupoProduto = grupoProdutoRepo.findById(dto.getGrupoProduto())
                .orElseThrow(() -> new DataIntegrityViolationException(
                        "Grupo de Produto -" + dto.getGrupoProduto() + " não está cadastrado!"));

        entity.setGrupoProduto(grupoProduto);

        return produtoRepo.save(entity);
    }

    public void delete(Long id) {
        Produto obj = findbyId(id);
        produtoRepo.deleteById(id);
    }

    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity.setQtdEstoque(dto.getQtdEstoque());
        entity.setImagem(dto.getImagem()); // ✅ campo novo
    }
}
