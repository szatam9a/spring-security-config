package dev.traning.securitysettings.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity.csrf(csrf -> csrf.disable())
                        .authorizeRequests(auth -> {
                            auth.antMatchers("/").permitAll();
                            auth.antMatchers("/user").hasRole("USER");
                            auth.antMatchers("/admin").hasRole("ADMIN");
                            auth.anyRequest().authenticated();
                        })
                        .formLogin(Customizer.withDefaults())
                        .build();
/*
                httpSecurity
                        .csrf()
                        .disable()
                        .authorizeRequests()
                        .antMatchers("/").permitAll()
                        .antMatchers("/user").hasRole("USER")
                        .antMatchers("/ADMIN").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        .and()
                        .httpBasic()
                        .and().build();
*/
    }

/*         @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("joe")
                .password("doe")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("jack")
                .password("doe")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
*/
}
