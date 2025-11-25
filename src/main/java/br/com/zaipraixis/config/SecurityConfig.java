package br.com.zaipraixis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // habilita CORS usando o bean corsConfigurationSource()
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // desabilita CSRF (para API stateless / cliente SPA)
                .csrf(csrf -> csrf.disable())
                // BYPASS: libera tudo (todas as rotas, todos os métodos)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Origens permitidas (ajuste se precisar)
        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://127.0.0.1:4200"
        ));

        // Métodos permitidos
        config.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));

        // Headers permitidos
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Cache-Control",
                "Content-Type"
        ));

        // Permite envio de cookies/credenciais (se precisar futuramente)
        config.setAllowCredentials(true);

        // Tempo em segundos que o preflight (OPTIONS) pode ficar em cache
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // aplica essa config para todos os endpoints
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
