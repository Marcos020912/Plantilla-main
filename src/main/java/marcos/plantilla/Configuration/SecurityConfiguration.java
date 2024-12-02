/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marcos.plantilla.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("Path/PermitedURI_1", "Path/PermitedURI_2").permitAll();
                    // Reemplazar "Path/PermitedURI_1","Path/PermitedURI_2" con las url que quieras liberar
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                .successHandler(successHandler())
                // URL a donde se va a ir después de iniciar sesión
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                // ALWAYS - IF_REQUIRED - NEVER - STATELESS
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
            response.sendRedirect("Path/RedirectAfterLogin");
            // reemplaza "Path/RedirectAfterLogin" por la URI a la que redirigir si es exitoso el login
        };
    }
}
