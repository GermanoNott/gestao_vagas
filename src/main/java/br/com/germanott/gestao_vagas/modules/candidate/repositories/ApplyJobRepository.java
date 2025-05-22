package br.com.germanott.gestao_vagas.modules.candidate.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.germanott.gestao_vagas.modules.candidate.entities.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
    // Custom query methods can be defined here if needed
    // For example, to find all applications by a specific candidate:
    // List<ApplyJobEntity> findByCandidateId(UUID candidateId);

}
