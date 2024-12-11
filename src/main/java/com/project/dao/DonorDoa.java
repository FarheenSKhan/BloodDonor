package com.project.dao;

import com.project.entity.Donor;
import com.project.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DonorDoa {

    @Autowired
    private DonorRepository donorRepository;

    public Donor saveDonor(Donor donor){
        return donorRepository.save(donor);
    }

    public Donor updateDonor(Donor donor){
        return donorRepository.save(donor);
    }

    public void  deleteDonor(Donor donor){
        donorRepository.delete(donor);
    }

    public Donor findByEmail(String email){
        return donorRepository.findByEmail(email);
    }

    public Donor findByMobile(String mobile){
        return donorRepository.findByMobile(mobile);
    }

    public Donor findById(Long id){
        Optional<Donor> optional=donorRepository.findById(id);
        return optional.orElse(null);
    }

    public Page<Donor> findAll(int page,int pageSize,String field){
        return donorRepository.findAll(PageRequest.of(page,pageSize).withSort(Sort.by(field)));
    }

    public Page<Donor> findByBloodGroup(int page, int pageSize, String field, String bloodGroup){
        return donorRepository.findByBloodGroup(PageRequest.of(page,pageSize).withSort(Sort.by(field)),bloodGroup);
    }
    public List<Donor> findByCityAndState(String city, String state){
        return donorRepository.findByCityAndState(city,state);
    }

    public List<Donor> findBYCityAndStateAndAge(String city,String state,int age){
        return donorRepository.findByCityAndStateAndAge(city, state, age);
    }

}
