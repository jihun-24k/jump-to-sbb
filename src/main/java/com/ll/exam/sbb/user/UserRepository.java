package com.ll.exam.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}