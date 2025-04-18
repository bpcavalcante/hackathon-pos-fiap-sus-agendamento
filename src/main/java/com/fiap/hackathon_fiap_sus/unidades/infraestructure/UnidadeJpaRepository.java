package com.fiap.hackathon_fiap_sus.unidades.infraestructure;

import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeJpaRepository extends JpaRepository<UnidadeEntity, Long> {}
