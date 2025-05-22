package br.com.germanott.gestao_vagas.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.germanott.gestao_vagas.modules.candidate.entities.CandidateEntity;

// camada de comunicação com o banco de dados
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    // JpaRepository provides CRUD operations and more for the entity
    // The first parameter is the entity type, and the second is the type of the
    // primary key.
    // No additional methods are needed for basic CRUD operations
    // JpaRepository already provides methods like save(), findById(), deleteById(),
    // etc.
    // You can define custom query methods here if needed
    // For example, you can add a method to find candidates by their username:
    // List<CandidateEntity> findByUsername(String username);
    // Or you can add a method to find candidates by their email:

    // Validação para o email e username serem únicos
    // Optional é uma classe que pode conter um valor ou não
    // Isso é útil para evitar NullPointerExceptions
    // Mas da acesso a mais funcionalidades
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    // This method will check if a candidate with the given username or email
    // already exists in the database.
    // If a candidate with the same username or email already exists, it will return
    // an Optional containing that candidate.
    // Otherwise, it will return an empty Optional.
    Optional<CandidateEntity> findByUsername(String username);
}
