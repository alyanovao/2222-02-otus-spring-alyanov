package ru.otus.springwork16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork16.model.StorageUser;

import java.util.Optional;

public interface UserDao extends JpaRepository<StorageUser, Long> {
    Optional<StorageUser> findByLogin(String login);
}
