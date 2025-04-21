package com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.domain.ports.AgendamentoRepositoryPort;
import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.AgendamentoJpaRepository;
import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.entities.AgendamentoEntity;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.UnidadeJpaRepository;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.UsuarioJpaRepository;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgendamentoSqlRepositoryImpl implements AgendamentoRepositoryPort {

  private final AgendamentoJpaRepository agendamentoJpaRepository;
  private final ProfissionalJpaRepository profissionalJpaRepository;
  private final UsuarioJpaRepository usuarioJpaRepository;
  private final UnidadeJpaRepository unidadeJpaRepository;


  @Override
  public boolean existsByProfissionalAndHorario(Long profissionalId, LocalDateTime dataAgendamento) {
    return agendamentoJpaRepository.existsByProfissionalIdAndDataAgendamento(profissionalId, dataAgendamento);
  }

  @Override
  public boolean existsByUsuarioAndHorario(Long usuarioId, LocalDateTime dataAgendamento) {
    return agendamentoJpaRepository.existsByUsuarioIdAndDataAgendamento(usuarioId, dataAgendamento);
  }

  @Override
  public AgendamentoDTO save(AgendamentoDTO dto) {
    ProfissionalEntity profissional = profissionalJpaRepository.findById(dto.getProfissionalId())
        .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

    UsuarioEntity usuario = usuarioJpaRepository.findById(dto.getUsuarioId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    UnidadeEntity unidade = unidadeJpaRepository.findById(dto.getUnidadeSaudeId())
        .orElseThrow(() -> new RuntimeException("Unidade de saúde não encontrada"));

    AgendamentoEntity entity =
        AgendamentoEntity.builder()
            .protocolo(dto.getProtocolo())
            .dataAgendamento(dto.getDataAgendamento())
            .status(dto.getStatus())
            .profissional(profissional)
            .usuario(usuario)
            .unidadeSaude(unidade)
            .build();

    AgendamentoEntity saved = agendamentoJpaRepository.save(entity);

    return AgendamentoDTO.builder()
        .id(saved.getId())
        .protocolo(saved.getProtocolo())
        .status(saved.getStatus())
        .dataAgendamento(saved.getDataAgendamento())
        .usuarioId(saved.getUsuario().getId())
        .profissionalId(saved.getProfissional().getId())
        .unidadeSaudeId(saved.getUnidadeSaude().getId())
        .build();
  }

  @Override
  public List<AgendamentoDTO> findByUsuarioId(Long usuarioId) {
    return agendamentoJpaRepository.findByUsuarioId(usuarioId)
        .stream()
        .map(agendamento -> AgendamentoDTO.builder()
            .id(agendamento.getId())
            .protocolo(agendamento.getProtocolo())
            .status(agendamento.getStatus())
            .dataAgendamento(agendamento.getDataAgendamento())
            .usuarioId(agendamento.getUsuario().getId())
            .profissionalId(agendamento.getProfissional().getId())
            .unidadeSaudeId(agendamento.getUnidadeSaude().getId())
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public Optional<AgendamentoDTO> findById(Long id) {
    return agendamentoJpaRepository.findById(id).map(AgendamentoEntity::toDTO);
  }

  @Override
  public AgendamentoDTO updateStatus(AgendamentoDTO dto) {
    AgendamentoEntity entity = agendamentoJpaRepository.findById(dto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com ID: " + dto.getId()));

    entity.setStatus(dto.getStatus());

    AgendamentoEntity updated = agendamentoJpaRepository.save(entity);

    return AgendamentoDTO.builder()
        .id(updated.getId())
        .protocolo(updated.getProtocolo())
        .status(updated.getStatus())
        .dataAgendamento(updated.getDataAgendamento())
        .usuarioId(updated.getUsuario().getId())
        .profissionalId(updated.getProfissional().getId())
        .unidadeSaudeId(updated.getUnidadeSaude().getId())
        .build();
  }

}
