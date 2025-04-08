package com.fiap.hackathon_fiap_sus.profissionais.application.ports;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;

public interface CadastrarProfissionalUseCasePorts {
  ProfissionalDTO cadastrar(ProfissionalDTO profissionalDTO);
}
