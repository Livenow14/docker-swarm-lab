package com.livenow.swarmloginservice.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccount(String account);

    boolean existsByEmail(String email);

    Optional<User> findByAccount(String account);
}
