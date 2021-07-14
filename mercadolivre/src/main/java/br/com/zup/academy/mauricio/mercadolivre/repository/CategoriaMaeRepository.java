package br.com.zup.academy.mauricio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.academy.mauricio.mercadolivre.model.CategoriaMae;

@Repository
public interface CategoriaMaeRepository extends JpaRepository<CategoriaMae,Long> {

}
