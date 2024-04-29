package com.example.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account.domain.AccountUser;

public interface AccountUserRepository extends JpaRepository<AccountUser, Long>{

}
