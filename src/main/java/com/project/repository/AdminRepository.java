package com.project.repository;

import com.project.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin findByEmail(String email);
    public Admin findByMobile(String mobile);

}