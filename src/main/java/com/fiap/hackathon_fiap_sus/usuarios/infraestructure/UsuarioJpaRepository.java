package com.fiap.hackathon_fiap_sus.usuarios.infraestructure;

import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {}
