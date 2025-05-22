package br.com.germanott.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.germanott.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    // Custom query methods can be defined here if needed
    // For example, find jobs by company ID, job title, etc.

    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
