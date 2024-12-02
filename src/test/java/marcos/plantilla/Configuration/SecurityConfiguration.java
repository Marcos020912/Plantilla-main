package marcos.plantilla.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("PATH/ToPermit1", "PATH/ToPermit2").permitAll();
                    // Reemplazar "PATH/ToPermit1","PATH/ToPermit2" con las URIs que deseas permitir libre.
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                .successHandler(successHandler())
                // URL a donde se va a ir después de iniciar sesión
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // ALWAYS - IF_REQUIRED - NEVER - STATELESS
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .sessionRegistry(sessionRegistry())
                .and()
                .sessionFixation()
                .migrateSession() // migrateSession - newSession - none
                .and()
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("PATH/ToRedirect");
            // "PATH/ToRedirect" reemplazar por la URI luego del login
        };
    }
}
