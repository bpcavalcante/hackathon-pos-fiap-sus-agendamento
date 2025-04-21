package com.fiap.hackathon_fiap_sus.agendamentos.infraestructure;

import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.entities.AgendamentoEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoEntity,Long> {

  boolean existsByProfissionalIdAndDataAgendamento(Long profissionalId, LocalDateTime dataAgendamento);
  boolean existsByUsuarioIdAndDataAgendamento(Long usuarioId, LocalDateTime dataAgendamento);
  List<AgendamentoEntity> findByUsuarioId(Long usuarioId);
}
