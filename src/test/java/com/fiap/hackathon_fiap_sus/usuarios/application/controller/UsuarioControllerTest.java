package com.fiap.hackathon_fiap_sus.usuarios.application.controller;

import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.input.UsuarioInput;
import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.output.UsuarioOutput;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.AtualizarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.BuscarPorIDUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.CadastrarUsuarioUseCasePorts;
import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

  @InjectMocks
  private UsuarioController controller;

  @Mock
  private CadastrarUsuarioUseCasePorts cadastrarUsuarioUseCasePorts;

  @Mock
  private BuscarPorIDUsuarioUseCasePorts buscarPorIDUsuarioUseCasePorts;

  @Mock
  private AtualizarUsuarioUseCasePorts atualizarUsuarioUseCasePorts;

  private UsuarioDTO mockDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockDTO = UsuarioDTO.builder()
        .id(1L)
        .nome("João Silva")
        .email("joao@email.com")
        .cpf("12345678900")
        .telefone("85999999999")
        .dataNascimento(LocalDate.of(1990, 1, 1))
        .build();
  }

  @Test
  void testCadastrarUsuario() {
    UsuarioInput input = new UsuarioInput();
    input.setNome("João Silva");
    input.setEmail("joao@email.com");
    input.setCpf("12345678900");
    input.setTelefone("85999999999");
    input.setDataNascimento(LocalDate.of(1990, 1, 1));

    when(cadastrarUsuarioUseCasePorts.cadastrarUsuario(any())).thenReturn(mockDTO);

    ResponseEntity<UsuarioOutput> response = controller.cadastrarUsuario(input);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("João Silva", response.getBody().getNome());
  }

  @Test
  void testBuscarUsuarioPorId() {
    when(buscarPorIDUsuarioUseCasePorts.buscarPorId(1L)).thenReturn(mockDTO);

    ResponseEntity<UsuarioOutput> response = controller.buscarUsuarioPorId(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("João Silva", response.getBody().getNome());
  }

  @Test
  void testAtualizarUsuario() {
    UsuarioInput input = new UsuarioInput();
    input.setNome("João Silva");
    input.setEmail("joao@email.com");
    input.setCpf("12345678900");
    input.setTelefone("85999999999");
    input.setDataNascimento(LocalDate.of(1990, 1, 1));

    when(atualizarUsuarioUseCasePorts.atualizarUsuario(eq(1L), any())).thenReturn(mockDTO);

    ResponseEntity<UsuarioOutput> response = controller.atualizarUsuario(1L, input);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("João Silva", response.getBody().getNome());
  }
}
