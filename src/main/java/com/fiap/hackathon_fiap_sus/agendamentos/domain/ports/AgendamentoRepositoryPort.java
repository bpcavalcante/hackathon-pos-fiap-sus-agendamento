package com.fiap.hackathon_fiap_sus.agendamentos.domain.ports;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepositoryPort {
  boolean existsByProfissionalAndHorario(Long profissionalId, LocalDateTime dataAgendamento);
  boolean existsByUsuarioAndHorario(Long usuarioId, LocalDateTime dataAgendamento);
  AgendamentoDTO save(AgendamentoDTO dto);
  List<AgendamentoDTO> findByUsuarioId(Long usuarioId);
  Optional<AgendamentoDTO> findById(Long id);
  AgendamentoDTO updateStatus(AgendamentoDTO dto);
}
