package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String> {

    Page<Account> findAllByStatus(Pageable pageable, int status);


    /*@Query("select a from Account as a where a.status = :status and a.createdAtMLS >= :createdAtMLS")
    Page<Account> findAllActiveAccount(@Param("status") int status, @Param("createdAtMLS") long createdAtMLS, Pageable pageable);*/
}
