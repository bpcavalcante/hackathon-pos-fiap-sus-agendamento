package com.fiap.hackathon_fiap_sus.usuarios.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.UsuarioJpaRepository;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioSqlRepositoryImplTest {

  private UsuarioJpaRepository usuarioJpaRepository;
  private UsuarioSqlRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    usuarioJpaRepository = mock(UsuarioJpaRepository.class);
    repository = new UsuarioSqlRepositoryImpl(usuarioJpaRepository);
  }

  @Test
  void deveSalvarUsuarioComSucesso() {
    UsuarioDatabaseDTO dto = UsuarioDatabaseDTO.builder()
        .id(null)
        .nome("Carlos Dias")
        .email("carlos@email.com")
        .telefone("85998887777")
        .cpf("12312312300")
        .dataNascimento(LocalDate.of(1985, 12, 1))
        .build();

    UsuarioEntity entitySalvo = UsuarioEntity.builder()
        .id(1L)
        .nome(dto.getNome())
        .email(dto.getEmail())
        .telefone(dto.getTelefone())
        .cpf(dto.getCpf())
        .dataNascimento(dto.getDataNascimento())
        .build();

    when(usuarioJpaRepository.save(any())).thenReturn(entitySalvo);

    UsuarioDatabaseDTO resultado = repository.save(dto);

    assertNotNull(resultado);
    assertEquals("Carlos Dias", resultado.getNome());
    verify(usuarioJpaRepository).save(any(UsuarioEntity.class));
  }

  @Test
  void deveLancarExcecaoAoSalvarUsuario() {
    UsuarioDatabaseDTO dto = UsuarioDatabaseDTO.builder()
        .nome("Erro Teste")
        .build();

    when(usuarioJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

    RuntimeException ex = assertThrows(RuntimeException.class, () -> {
      repository.save(dto);
    });

    assertTrue(ex.getMessage().contains("Erro ao tentar salvar o usuario"));
    verify(usuarioJpaRepository).save(any());
  }

  @Test
  void deveRetornarUsuarioQuandoIdExiste() {
    UsuarioEntity entity = UsuarioEntity.builder()
        .id(1L)
        .nome("Ana Paula")
        .cpf("22233344455")
        .email("ana@email.com")
        .telefone("85991112222")
        .dataNascimento(LocalDate.of(1992, 5, 20))
        .build();

    when(usuarioJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

    Optional<Usuario> result = repository.findById(1L);

    assertTrue(result.isPresent());
    assertEquals("Ana Paula", result.get().getNome());
    verify(usuarioJpaRepository).findById(1L);
  }

  @Test
  void deveRetornarOptionalVazioQuandoUsuarioNaoExiste() {
    when(usuarioJpaRepository.findById(99L)).thenReturn(Optional.empty());

    Optional<Usuario> result = repository.findById(99L);

    assertTrue(result.isEmpty());
    verify(usuarioJpaRepository).findById(99L);
  }
}
