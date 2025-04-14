package com.fiap.hackathon_fiap_sus.profissionais.infraestructure;

import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalEntity, Long> {
  List<ProfissionalEntity> findByEspecialidade(String especialidade);
}
