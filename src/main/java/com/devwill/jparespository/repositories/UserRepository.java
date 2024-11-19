package com.devwill.jparespository.repositories;

import com.devwill.jparespository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
