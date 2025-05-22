package br.com.germanott.gestao_vagas.modules.candidate.useCases;

import br.com.germanott.gestao_vagas.modules.company.controllers.AuthCompanyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.germanott.gestao_vagas.exceptions.UserFoundException;
import br.com.germanott.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.germanott.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service // This annotation indicates that the class is a service component in the
// Spring framework. It is used to mark the class as a service layer component
// and allows Spring to manage its lifecycle and dependencies.
public class CreateCandidateUseCase {

    private final AuthCompanyController authCompanyController;

    @Autowired // This annotation is used to inject the CandidateController bean
    // into the current class. It allows Spring to manage the lifecycle of the
    // CandidateController and inject it where needed.
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    CreateCandidateUseCase(AuthCompanyController authCompanyController) {
        this.authCompanyController = authCompanyController;
    }
    // O PasswordEncoder é uma interface do Spring Security que fornece métodos
    // para codificar e verificar senhas. O método encode é usado para codificar
    // a senha do usuário antes de armazená-la no banco de dados.

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(),
                        candidateEntity.getEmail())
                .ifPresent((user) -> {
                    // Usuou o UserFoundException para lançar uma exceção personalizada
                    // quando o usuário já existe
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        // O PasswordEncoder ta usando um @Bean para codificar a senha
        // antes de armazená-la no banco de dados. Está sendo criado no SecurityConfig.

        return this.candidateRepository.save(candidateEntity);
    }
}
