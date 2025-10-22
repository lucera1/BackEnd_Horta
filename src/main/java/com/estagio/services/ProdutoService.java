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

    public List<ProdutoDTO> findAll(){
        //retorna uma lista de ProdutoDTO
        return produtoRepo.findAll().stream().map
                (obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
    }

    public Produto findbyId(Long id){
        Optional<Produto> obj = produtoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: "+id));
    }


    public Produto create(ProdutoDTO dto){
        dto.setId(null);

        // Buscar o grupo de produto
        GrupoProduto grupoProduto = grupoProdutoRepo.findById(dto.getGrupoProduto())
                .orElseThrow(() -> new DataIntegrityViolationException(
                        "Grupo de Produto -" + dto.getGrupoProduto() + " não está cadastrado!"));

        // Criar o produto
        Produto obj = new Produto();
        obj.setNome(dto.getNome());
        obj.setPreco(dto.getPreco());
        obj.setQtdEstoque(dto.getQtdEstoque());
        obj.setGrupoProduto(grupoProduto); // associa o grupo existente
        // A descrição já vem do grupo, não precisa setar dto.descricao

        // Salvar e retornar
        return produtoRepo.save(obj);
    }


    public void validaProduto(ProdutoDTO dto){
        Optional<GrupoProduto> grupoProduto = grupoProdutoRepo.findById(dto.getGrupoProduto());
        if(!grupoProduto.isPresent()){
            throw new DataIntegrityViolationException("Grupo de Produto -"+ dto.getGrupoProduto() +" não está cadastrado!");
        }

    }

    public Produto update(Long id, ProdutoDTO objDto){
        objDto.setId(id);
        Produto oldObj = findbyId(id);
        oldObj = new Produto(objDto);
        return produtoRepo.save(oldObj);
    }

    public void delete(Long id){
        Produto obj = findbyId(id);
        produtoRepo.deleteById(id);
    }

}
