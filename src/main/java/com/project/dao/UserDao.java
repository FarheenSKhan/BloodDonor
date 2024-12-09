package com.project.dao;

import com.project.entity.Donor;
import com.project.entity.User;
import com.project.repository.DonorRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonorRepository donorRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }



    public User findById(Long id){
        Optional<User> optional=userRepository.findById(id);
        return optional.orElse(null);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User findByMobile(String mobile){
        return userRepository.findByMobile(mobile);
    }
    public User findByOtp(Long otp){
        return  userRepository.findByOtp(otp);
    }
    public Page<User> findAll(int page, int pageSize, String field){
        return userRepository.findAll(PageRequest.of(page,pageSize).withSort(Sort.by(field)));
    }


}
