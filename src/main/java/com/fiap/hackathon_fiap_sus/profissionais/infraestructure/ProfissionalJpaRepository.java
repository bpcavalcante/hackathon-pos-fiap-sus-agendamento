package com.fiap.hackathon_fiap_sus.profissionais.infraestructure;

import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalEntity, Long> {}
