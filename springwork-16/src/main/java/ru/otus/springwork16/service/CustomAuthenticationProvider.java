package ru.otus.springwork16.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.otus.springwork16.exception.UserNotFoundException;
import ru.otus.springwork16.model.StorageUser;
import ru.otus.springwork16.repository.UserDao;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDao dao;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        StorageUser storageUser = dao.findByLogin(userName).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (encoder.matches(password + storageUser.getSalt(), storageUser.getHash())) {
           UserDetails principal =  User.builder()
                   .username(storageUser.getLogin())
                   .password(storageUser.getHash())
                   .authorities(storageUser.getRole())
                   .build();
           return new UsernamePasswordAuthenticationToken(principal, storageUser.getHash(), principal.getAuthorities());
        }
        throw new BadCredentialsException("Bad login or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
