package com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDatabaseDTO {

  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private LocalDate dataNascimento;
  private String cpf;

  public UsuarioDTO toDTO() {
    return UsuarioDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .telefone(this.telefone)
        .dataNascimento(this.dataNascimento)
        .cpf(this.cpf)
        .build();
  }
}
