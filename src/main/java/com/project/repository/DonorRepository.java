package com.project.repository;

import com.fasterxml.jackson.databind.node.DecimalNode;
import com.project.entity.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    public Donor findByEmail(String email);
    public Donor findByMobile(String mobile);
    public Page<Donor> findByBloodGroup(Pageable pageable,String bloodGroup);
    public Page<Donor> findAll(Pageable pageable);

    public List<Donor> findByCityAndState(String city, String state);
    public List<Donor> findByCityAndStateAndAge(String city, String state, int age);

}