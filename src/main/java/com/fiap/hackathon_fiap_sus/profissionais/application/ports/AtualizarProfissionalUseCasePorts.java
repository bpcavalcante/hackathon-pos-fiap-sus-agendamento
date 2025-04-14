package com.fiap.hackathon_fiap_sus.profissionais.application.ports;

import com.fiap.hackathon_fiap_sus.profissionais.application.ports.dto.ProfissionalDTO;

public interface AtualizarProfissionalUseCasePorts {

  ProfissionalDTO atualizar(Long id, ProfissionalDTO profissionalDTO);

}
