package com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.agendamentos.application.ports.dto.AgendamentoDTO;
import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.AgendamentoJpaRepository;
import com.fiap.hackathon_fiap_sus.agendamentos.infraestructure.entities.AgendamentoEntity;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.ProfissionalJpaRepository;
import com.fiap.hackathon_fiap_sus.profissionais.infraestructure.entities.ProfissionalEntity;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.UnidadeJpaRepository;
import com.fiap.hackathon_fiap_sus.unidades.infraestructure.entities.UnidadeEntity;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.UsuarioJpaRepository;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendamentoSqlRepositoryImplTest {

  private AgendamentoJpaRepository agendamentoJpaRepository;
  private ProfissionalJpaRepository profissionalJpaRepository;
  private UsuarioJpaRepository usuarioJpaRepository;
  private UnidadeJpaRepository unidadeJpaRepository;

  private AgendamentoSqlRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    agendamentoJpaRepository = mock(AgendamentoJpaRepository.class);
    profissionalJpaRepository = mock(ProfissionalJpaRepository.class);
    usuarioJpaRepository = mock(UsuarioJpaRepository.class);
    unidadeJpaRepository = mock(UnidadeJpaRepository.class);

    repository = new AgendamentoSqlRepositoryImpl(
        agendamentoJpaRepository,
        profissionalJpaRepository,
        usuarioJpaRepository,
        unidadeJpaRepository
    );
  }

  private AgendamentoDTO buildDTO() {
    return AgendamentoDTO.builder()
        .id(1L)
        .protocolo("AGD-001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.of(2025, 4, 20, 10, 0))
        .usuarioId(100L)
        .profissionalId(200L)
        .unidadeSaudeId(300L)
        .build();
  }

  @Test
  void testExistsByProfissionalAndHorario() {
    LocalDateTime horario = LocalDateTime.of(2025, 4, 20, 10, 0);

    when(agendamentoJpaRepository.existsByProfissionalIdAndDataAgendamento(1L, horario)).thenReturn(true);

    assertTrue(repository.existsByProfissionalAndHorario(1L, horario));
  }


  @Test
  void testExistsByUsuarioAndHorario() {
    when(agendamentoJpaRepository.existsByUsuarioIdAndDataAgendamento(1L, LocalDateTime.now())).thenReturn(false);
    assertFalse(repository.existsByUsuarioAndHorario(1L, LocalDateTime.now()));
  }

  @Test
  void testSave() {
    AgendamentoDTO dto = buildDTO();
    ProfissionalEntity profissional = ProfissionalEntity.builder().id(200L).build();
    UsuarioEntity usuario = UsuarioEntity.builder().id(100L).build();
    UnidadeEntity unidade = UnidadeEntity.builder().id(300L).build();

    when(profissionalJpaRepository.findById(200L)).thenReturn(Optional.of(profissional));
    when(usuarioJpaRepository.findById(100L)).thenReturn(Optional.of(usuario));
    when(unidadeJpaRepository.findById(300L)).thenReturn(Optional.of(unidade));

    AgendamentoEntity entity = AgendamentoEntity.builder()
        .id(1L)
        .protocolo("AGD-001")
        .status("AGENDADO")
        .dataAgendamento(dto.getDataAgendamento())
        .profissional(profissional)
        .usuario(usuario)
        .unidadeSaude(unidade)
        .build();

    when(agendamentoJpaRepository.save(any())).thenReturn(entity);

    AgendamentoDTO result = repository.save(dto);

    assertEquals(dto.getProtocolo(), result.getProtocolo());
    assertEquals("AGENDADO", result.getStatus());
  }

  @Test
  void testFindByUsuarioId() {
    UsuarioEntity usuario = UsuarioEntity.builder().id(100L).build();
    ProfissionalEntity profissional = ProfissionalEntity.builder().id(200L).build();
    UnidadeEntity unidade = UnidadeEntity.builder().id(300L).build();

    AgendamentoEntity entity = AgendamentoEntity.builder()
        .id(1L)
        .protocolo("AGD-001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.of(2025, 4, 20, 10, 0))
        .usuario(usuario)
        .profissional(profissional)
        .unidadeSaude(unidade)
        .build();

    when(agendamentoJpaRepository.findByUsuarioId(100L)).thenReturn(List.of(entity));

    List<AgendamentoDTO> result = repository.findByUsuarioId(100L);

    assertEquals(1, result.size());
    assertEquals("AGD-001", result.get(0).getProtocolo());
  }

  @Test
  void testFindById() {
    AgendamentoEntity entity = AgendamentoEntity.builder()
        .id(1L)
        .protocolo("AGD-001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.now())
        .usuario(UsuarioEntity.builder().id(1L).build())
        .profissional(ProfissionalEntity.builder().id(2L).build())
        .unidadeSaude(UnidadeEntity.builder().id(3L).build())
        .build();

    when(agendamentoJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

    Optional<AgendamentoDTO> result = repository.findById(1L);

    assertTrue(result.isPresent());
    assertEquals("AGD-001", result.get().getProtocolo());
  }

  @Test
  void testUpdateStatus() {
    AgendamentoEntity entity = AgendamentoEntity.builder()
        .id(1L)
        .protocolo("AGD-001")
        .status("AGENDADO")
        .dataAgendamento(LocalDateTime.now())
        .usuario(UsuarioEntity.builder().id(1L).build())
        .profissional(ProfissionalEntity.builder().id(2L).build())
        .unidadeSaude(UnidadeEntity.builder().id(3L).build())
        .build();

    when(agendamentoJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

    AgendamentoEntity updatedEntity = entity;
    updatedEntity.setStatus("CANCELADO");

    when(agendamentoJpaRepository.save(entity)).thenReturn(updatedEntity);

    AgendamentoDTO dto = AgendamentoDTO.builder()
        .id(1L)
        .status("CANCELADO")
        .build();

    AgendamentoDTO result = repository.updateStatus(dto);

    assertEquals("CANCELADO", result.getStatus());
  }
}
