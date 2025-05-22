package br.com.germanott.gestao_vagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.germanott.gestao_vagas.exceptions.UserFoundException;
import br.com.germanott.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.germanott.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(),
                        companyEntity.getEmail())
                .ifPresent((user) -> {
                    // Usuou o UserFoundException para lançar uma exceção personalizada
                    // quando o usuário já existe
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        // O PasswordEncoder é uma interface do Spring Security que fornece métodos
        // para codificar e verificar senhas. O método encode é usado para codificar
        // a senha do usuário antes de armazená-la no banco de dados. Isso é importante
        // para garantir que as senhas sejam armazenadas de forma segura e não possam
        // ser lidas em texto simples. O PasswordEncoder usa algoritmos de hash
        // seguros para codificar as senhas, tornando-as difíceis de serem quebradas
        // por ataques de força bruta ou outras técnicas de quebra de senha.
        // O método encode retorna a senha codificada, que é então definida na
        // entidade CompanyEntity. Isso garante que a senha armazenada no banco de
        // dados seja sempre a versão codificada, o que é uma prática recomendada
        // de segurança.
        // Codifica a senha usando o PasswordEncoder
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
