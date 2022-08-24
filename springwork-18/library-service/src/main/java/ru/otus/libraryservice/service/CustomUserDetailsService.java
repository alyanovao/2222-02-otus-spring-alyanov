package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.libraryservice.exception.UserNotFoundException;
import ru.otus.libraryservice.model.StorageUser;
import ru.otus.libraryservice.repository.UserDao;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        StorageUser storageUser = dao.findByLogin(username).orElseThrow(UserNotFoundException::new);

        UserDetails user = User.builder()
                .username(storageUser.getLogin())
                .password(storageUser.getSalt())
                .authorities(storageUser.getRole())
                .build();
        return user;
    }
}

