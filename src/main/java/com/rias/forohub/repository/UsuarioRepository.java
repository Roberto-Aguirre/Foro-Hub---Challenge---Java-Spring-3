package com.rias.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.rias.forohub.domain.usuario.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    UserDetails findByNombre(String nombre);
}   
