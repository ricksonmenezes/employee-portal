/*
package com.rgarage.employeeportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



public class SecurityConfig {


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        */
/*http
                .authorizeHttpRequests((authz)->authz
                        .requestMatchers("/signin").permitAll()
                        .requestMatchers("/employees").permitAll()
                        .requestMatchers("/playground").permitAll()
                        .requestMatchers("/graphql").permitAll()
                        .requestMatchers("/api/vendor/playground").permitAll()
                        .anyRequest().authenticated());
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/graphql"));

        http.formLogin(ft->ft
                //.loginPage("/signin").permitAll() --> this was making a circular view
                .defaultSuccessUrl("/home", true));

        http
                .logout(lt-> {

                    lt.logoutUrl("/logout")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .permitAll();
                });

        return http.build();*//*

        return null;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user =   User.builder()
                .username("user").password(encoder.encode("sa"))
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
*/
