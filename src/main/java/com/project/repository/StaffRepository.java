package com.project.repository;

import com.project.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    public List<Staff> findByDepartment(String department);
    public Staff findByEmail(String email);
    public Staff findByMobile(String mobile);
}