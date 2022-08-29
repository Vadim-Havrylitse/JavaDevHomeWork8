package com.example.JavaDevHomeWork8.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/users/save/**",
                        "/users/delete/**",
                        "/users/update/**",
                        "/products/save/**",
                        "/products/delete/**",
                        "/products/update/**",
                        "/manufacturers/save/**",
                        "/manufacturers/delete/**",
                        "/manufacturers/update/**")
                        .hasAuthority("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout()
                .logoutSuccessUrl("/login?logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(false);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
