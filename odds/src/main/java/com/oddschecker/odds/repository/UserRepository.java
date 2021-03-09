package com.oddschecker.odds.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oddschecker.odds.domain.Users;

public interface UserRepository extends JpaRepository<Users, UUID> {

    @Query(value = "select * from users where user_id=?1", nativeQuery = true)
    Users findByUserId(String userId);
}
