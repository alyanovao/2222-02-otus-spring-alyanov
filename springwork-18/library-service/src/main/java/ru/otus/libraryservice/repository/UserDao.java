package ru.otus.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryservice.model.StorageUser;

import java.util.Optional;

public interface UserDao extends JpaRepository<StorageUser, Long> {
    Optional<StorageUser> findByLogin(String login);
}
