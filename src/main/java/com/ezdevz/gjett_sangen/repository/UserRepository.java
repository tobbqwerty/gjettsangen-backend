package com.ezdevz.gjett_sangen.repository;

import com.ezdevz.gjett_sangen.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull List<User> findAll();

    Optional<User> findByEmail(@NonNull String email);

    Optional<User> findByUsername(@NonNull String username);

    Optional<User> findById(@NonNull Long id);
}
