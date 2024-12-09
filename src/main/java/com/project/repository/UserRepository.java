package com.project.repository;

import com.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    public User findByMobile(String mobile);

    public User findByOtp(Long otp);

    public Page<User> findAll(Pageable pageable);

}