package br.com.zup.academy.mauricio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
