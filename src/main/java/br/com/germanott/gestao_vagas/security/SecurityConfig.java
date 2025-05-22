package br.com.germanott.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration // Classe de configuração
@EnableMethodSecurity // Habilita a segurança em métodos
public class SecurityConfig {

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;

    private static final String[] SWAGGER_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resource/**"
    };

    // Configuração de segurança
    // Desabilita a proteção CSRF (Cross-Site Request Forgery)
    // Isso pode ser necessário para APIs REST, mas deve ser feito com cuidado
    // em aplicações web tradicionais.
    // O CSRF é uma proteção contra ataques que tentam executar ações não
    // autorizadas
    // em nome de um usuário autenticado. Desabilitar isso pode expor a aplicação
    // a esse tipo de ataque, então é importante garantir que a aplicação
    // esteja protegida de outras maneiras, como autenticação e autorização
    // adequadas.
    // O método securityFilterChain é responsável por configurar a segurança
    // da aplicação. Ele desabilita a proteção CSRF, o que pode ser necessário
    // para APIs REST, mas deve ser feito com cuidado em aplicações web
    // tradicionais.
    // O método retorna um objeto SecurityFilterChain, que é uma cadeia de filtros
    // que processa as requisições HTTP. Essa cadeia de filtros é responsável
    // por aplicar as regras de segurança definidas na configuração.
    // O método securityFilterChain é anotado com @Bean, o que significa que
    // ele será gerenciado pelo Spring e poderá ser injetado em outras partes
    // da aplicação. Isso permite que a configuração de segurança seja reutilizada
    // em diferentes partes da aplicação, se necessário.
    @Bean // Sobreescreve o método de configuração de segurança
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/candidate/").permitAll()
                            .requestMatchers("/company/").permitAll()
                            .requestMatchers("/company/auth").permitAll()
                            .requestMatchers("/candidate/auth").permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna um PasswordEncoder padrão, que pode ser usado para codificar
        // senhas. O PasswordEncoder é uma interface do Spring Security que
        // fornece métodos para codificar e verificar senhas.
        return new BCryptPasswordEncoder();
        // O BCryptPasswordEncoder é uma implementação do PasswordEncoder
        // que usa o algoritmo BCrypt para codificar senhas. O BCrypt é um
        // algoritmo de hash seguro e amplamente utilizado para armazenar senhas.
        // Ele é projetado para ser lento, o que dificulta ataques de força
        // bruta, e inclui um fator de custo que pode ser ajustado para aumentar
        // a segurança.
    }

}
