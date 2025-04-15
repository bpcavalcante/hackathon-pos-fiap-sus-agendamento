package com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.input;

import com.fiap.hackathon_fiap_sus.usuarios.application.ports.dto.UsuarioDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInput {

  private String nome;
  private String email;
  private String telefone;
  private Date dataNascimento;
  private String cpf;

  public UsuarioDTO toDTO() {
    return UsuarioDTO.builder()
        .nome(this.nome)
        .email(this.email)
        .telefone(this.telefone)
        .dataNascimento(this.dataNascimento)
        .cpf(this.cpf)
        .build();
  }
}
