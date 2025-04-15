package com.fiap.hackathon_fiap_sus.usuarios.infraestructure.implementations;

import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.UsuarioRepositoryPort;
import com.fiap.hackathon_fiap_sus.usuarios.domain.ports.dto.UsuarioDatabaseDTO;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.UsuarioJpaRepository;
import com.fiap.hackathon_fiap_sus.usuarios.infraestructure.entities.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class UsuarioSqlRepositoryImpl implements UsuarioRepositoryPort {

  private final UsuarioJpaRepository usuarioJpaRepository;

  @Override
  @Transactional
  public UsuarioDatabaseDTO save(UsuarioDatabaseDTO usuarioDatabaseDTO) {

    UsuarioEntity usuarioEntity = new UsuarioEntity();

    usuarioEntity.setNome(usuarioDatabaseDTO.getNome());
    usuarioEntity.setEmail(usuarioDatabaseDTO.getEmail());
    usuarioEntity.setTelefone(usuarioDatabaseDTO.getTelefone());
    usuarioEntity.setCpf(usuarioDatabaseDTO.getCpf());
    usuarioEntity.setDataNascimento(usuarioDatabaseDTO.getDataNascimento());

    try {
      return usuarioJpaRepository.save(usuarioEntity).toDataBaseDTO();
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao tentar salvar o usuario", e);
    }
  }
}
