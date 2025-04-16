package com.fiap.hackathon_fiap_sus.usuarios.domain;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private LocalDate dataNascimento;
  private String cpf;

  public UsuarioDatabaseDTO toDatabaseDTO() {
    return UsuarioDatabaseDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .telefone(this.telefone)
        .dataNascimento(this.dataNascimento)
        .cpf(this.cpf)
        .build();
  }

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
