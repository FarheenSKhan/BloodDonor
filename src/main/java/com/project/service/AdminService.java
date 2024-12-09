package com.project.service;

import com.project.dao.AdminDao;
import com.project.dto.request.AdminRequest;
import com.project.dto.request.StaffRequest;
import com.project.entity.Admin;
import com.project.entity.ResponseStructure;
import com.project.utill.Aes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;


    public ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminRequest request){
        ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
        if(adminDao.findByEmail(request.getEmail())!=null){
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Email already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
        if(adminDao.findByMobile(request.getMobile())!=null){
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Mobile number already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }else{
            Admin admin=new Admin();
            admin.setFirstName(request.getFirstName());
            admin.setLastName(request.getLastName());
            admin.setEmail(request.getEmail());
            admin.setMobile(request.getMobile());
            admin.setPassword(Aes.encrypt(request.getPassword()));
            admin.setAddress(request.getAddress());
            admin.setCity(request.getCity());
            admin.setProfilPic(request.getProfilPic());
            admin.setAadharNo(request.getAadharNo());
            admin.setRole("ROLE_ADMIN");
            admin.setName(request.getFirstName()+" "+request.getLastName());
            admin=adminDao.saveAdmin(admin);

            responseStructure.setStatus(HttpStatus.OK.CREATED.value());
            responseStructure.setMessage("Admin saved successfully");
            responseStructure.setData(admin);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<ResponseStructure<List<Admin>>> findAll(){
        ResponseStructure<List<Admin>> responseStructure=new ResponseStructure<>();
        List<Admin> admin = adminDao.findAll();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Admins found successfully");
        responseStructure.setData(admin);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Admin>> findById(Long id){
        ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
        Admin admin = adminDao.findById(id);
        if(Objects.isNull(admin)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Admin not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }else{
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Admin found successfully");
            responseStructure.setData(admin);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Admin>> findByEmail(String email){
        ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
        Admin admin = adminDao.findByEmail(email);
        if(Objects.isNull(admin)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Admin not found with email: "+email);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }else{
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Admin found successfully");
            responseStructure.setData(admin);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }
    public ResponseEntity<ResponseStructure<Admin>> findByMobile(String mobile){
        ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
        Admin admin = adminDao.findByMobile(mobile);
        if(Objects.isNull(admin)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Admin not found with mobile: "+mobile);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }else{
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Admin found successfully");
            responseStructure.setData(admin);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Admin>> updateAdmin(AdminRequest request, Long id){
        ResponseStructure<Admin> responseStructure=new ResponseStructure<>();
        Admin admin =adminDao.findById(id);
        if(Objects.isNull(admin)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Staff not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }else{
            admin.setFirstName(request.getFirstName());
            admin.setLastName(request.getLastName());
            admin.setEmail(request.getEmail());
            admin.setMobile(request.getMobile());
            admin.setProfilPic(request.getProfilPic());
            admin.setAddress(request.getAddress());
            admin.setCity(request.getCity());
            admin.setRole("ROLE_ADMIN");
            admin.setName(request.getFirstName()+" "+request.getLastName());
            admin =adminDao.updateAdmin(admin);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Staff updated successfully");
            responseStructure.setData(admin);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

}
