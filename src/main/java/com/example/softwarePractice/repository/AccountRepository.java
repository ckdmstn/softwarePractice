package com.example.softwarePractice.repository;

import com.example.softwarePractice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByIdAndPassword(String id, String password);
}
