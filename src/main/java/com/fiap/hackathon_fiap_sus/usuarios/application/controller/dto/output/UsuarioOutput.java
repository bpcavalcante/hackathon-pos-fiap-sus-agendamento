package com.fiap.hackathon_fiap_sus.usuarios.application.controller.dto.output;

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
public class UsuarioOutput {

  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private Date dataNascimento;
  private String cpf;

}
