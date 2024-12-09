package com.project.repository;

import com.project.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    public Donor findByEmail(String email);
    public Donor findByMobile(String mobile);
    public List<Donor> findByBloodGroup(String bloodGroup);
    //to do : apply pagination for bloogGroup
    // find donor by city , state,age  with pagination
}