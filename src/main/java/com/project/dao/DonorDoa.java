package com.project.dao;

import com.project.entity.Donor;
import com.project.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Donor> findAll(){
        return donorRepository.findAll();
    }

    public List<Donor> findByBloodGroup(String bloodGroup){
        return donorRepository.findByBloodGroup(bloodGroup);
    }


}
