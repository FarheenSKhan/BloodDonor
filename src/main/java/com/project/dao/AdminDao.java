package com.project.dao;

import com.project.entity.Admin;
import com.project.repository.AdminRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminDao {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin findById(Long id){
        Optional<Admin> optional=adminRepository.findById(id);
        return optional.orElse(null);
    }

    public Admin findByEmail(String email){
        return adminRepository.findByEmail(email);
    }

    public Admin findByMobile(String mobile){
        return adminRepository.findByMobile(mobile);
    }
    public Page<Admin> findAll(int page,int pageSize,String field){
        return adminRepository.findAll(PageRequest.of(page,pageSize).withSort(Sort.by(field)));
    }
  public Admin updateAdmin(Admin admin){
        return adminRepository.save(admin);
  }

}
