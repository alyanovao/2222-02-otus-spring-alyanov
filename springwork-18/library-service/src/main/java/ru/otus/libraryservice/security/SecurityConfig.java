package ru.otus.libraryservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.otus.libraryservice.service.CustomAuthenticationProvider;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    public void configure(WebSecurity web) {
        //web.ignoring().antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").authenticated()
                .and()
                .authorizeRequests().antMatchers("/author").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/authorEdit").hasAnyRole("ADMIN", "USER")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/authorEdit").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/kindBook").authenticated()
                .and()
                .authorizeRequests().antMatchers("/book").authenticated()
                .and()
                .authorizeRequests().antMatchers("/bookEdit").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/bookDelete").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/monitor").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/monitor/**").hasRole("ADMIN")
                .and()
                .formLogin();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
