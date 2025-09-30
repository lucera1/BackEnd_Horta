package com.estagio.repositories;

import com.estagio.domains.dtos.GrupoProdutoDTO;
import com.estagio.domains.produtos.GrupoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoProdutoRepository extends JpaRepository<GrupoProduto, Long> {
}
