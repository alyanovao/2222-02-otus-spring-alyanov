package ru.outs.springwork13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outs.springwork13.model.StorageUser;

import java.util.Optional;

public interface UserDao extends JpaRepository<StorageUser, Long> {
    Optional<StorageUser> findByLogin(String login);
}
