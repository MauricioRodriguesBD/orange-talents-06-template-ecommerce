package br.com.zup.academy.mauricio.mercadolivre.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.academy.mauricio.mercadolivre.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
