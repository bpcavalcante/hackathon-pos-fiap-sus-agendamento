package com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto;

import com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.output.UsuarioOutput;
import com.fiap.hackathon_fiap_sus.usuarios.domain.Usuario;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private Date dataNascimento;
  private String cpf;

  public Usuario toDomain() {
    return Usuario.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .telefone(this.telefone)
        .dataNascimento(this.dataNascimento)
        .cpf(this.cpf)
        .build();
  }

  public UsuarioOutput toOutput() {
    return UsuarioOutput.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .telefone(this.telefone)
        .dataNascimento(this.dataNascimento)
        .cpf(this.cpf)
        .build();
  }
}
