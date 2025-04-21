package com.fiap.hackathon_fiap_sus.usuarios.domain.usecase;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarUsuarioUseCaseTest {

  private UsuarioRepositoryPort repository;
  private CadastrarUsuarioUseCase useCase;

  @BeforeEach
  void setUp() {
    repository = mock(UsuarioRepositoryPort.class);
    useCase = new CadastrarUsuarioUseCase(repository);
  }

  @Test
  void deveCadastrarUsuarioComSucesso() {
    UsuarioDTO inputDTO = UsuarioDTO.builder()
        .nome("Maria Souza")
        .email("maria@email.com")
        .cpf("98765432100")
        .telefone("85988888888")
        .dataNascimento(LocalDate.of(1992, 3, 10))
        .build();

    UsuarioDatabaseDTO savedDTO = UsuarioDatabaseDTO.builder()
        .id(1L)
        .nome("Maria Souza")
        .email("maria@email.com")
        .cpf("98765432100")
        .telefone("85988888888")
        .dataNascimento(LocalDate.of(1992, 3, 10))
        .build();

    when(repository.save(any())).thenReturn(savedDTO);

    UsuarioDTO result = useCase.cadastrarUsuario(inputDTO);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("Maria Souza", result.getNome());
    verify(repository).save(any());
  }
}
