package com.afterschool.test.config;

import com.afterschool.test.Entity.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()))
                .authorizeHttpRequests((authorizaRequest) ->
                    authorizaRequest
                            .requestMatchers(PathRequest.toH2Console()).permitAll()
                            .requestMatchers("/","/login","/logout","/signup").permitAll()
                            .requestMatchers("/check").hasRole(Role.ADMIN.name())
                            .anyRequest().authenticated()
                );
        return http.build();

    }
}