package com.project.service;

import com.project.dao.DonorDoa;
import com.project.dao.UserDao;
import com.project.dto.request.DonorRequest;
import com.project.entity.Donor;
import com.project.entity.ResponseStructure;
import com.project.entity.User;
import com.project.utill.Aes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DonorService {

    @Autowired
    private DonorDoa donorDoa;

    @Autowired
    private UserDao userDao;

    public ResponseEntity<ResponseStructure<Donor>> saveDonor(DonorRequest request) {
        ResponseStructure<Donor> responseStructure = new ResponseStructure<>();
        if (donorDoa.findByEmail(request.getEmail()) != null) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Email already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
        if (donorDoa.findByMobile(request.getMobile()) != null) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Mobile number already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        } else {
            Donor donor =new Donor();
            donor.setEmail(request.getEmail());
            donor.setMobile(request.getMobile());
            donor.setPassword(Aes.encrypt(request.getPassword()));
            donor.setBloodGroup(request.getBloodGroup());
            donor.setAddress(request.getAddress());
            donor.setAge(request.getAge());
            donor.setFirstName(request.getFirstName());
            donor.setLastName(request.getLastName());
            donor.setActiveDonor(request.isActiveDonor());
            donor.setAadharNo(request.getAadharNo());
            donor.setCity(request.getCity());
            donor.setState(request.getState());
            donor.setProfilPic(request.getProfilPic());
            donor.setRole("ROLE_DONOR");
            donor.setName((request.getFirstName()+" "+request.getLastName()).toUpperCase());

            donor = donorDoa.saveDonor(donor);
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Donor saved successfully");
            responseStructure.setData(donor);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<ResponseStructure<User>> deleteDonor(Long id){
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user=userDao.findById(id);
        if (Objects.isNull(user)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Donor not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }else{
            userDao.deleteUser(user);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Donor deleted successfully");
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Page<Donor>>> findAll(int page,int pageSize,String feild){
        ResponseStructure<Page<Donor>> responseStructure = new ResponseStructure<>();
        Page<Donor> donors = donorDoa.findAll(page,pageSize,feild);
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Donors found successfully");
        responseStructure.setData(donors);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    public  ResponseEntity<ResponseStructure<Page<Donor>>> findByBloodGroup(int page, int pageSize, String field, String bloodGroup){
        ResponseStructure<Page<Donor>> responseStructure = new ResponseStructure<>();
        Page<Donor> donors = donorDoa.findByBloodGroup(page,pageSize,field,bloodGroup);
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Donors found successfully ");
        responseStructure.setData(donors);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    public  ResponseEntity<ResponseStructure<Donor>> findByEmail(String email) {
        ResponseStructure<Donor> responseStructure = new ResponseStructure<>();
        Donor donor = donorDoa.findByEmail(email);
        if (Objects.isNull(donor)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Donor not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Donor found successfully");
            responseStructure.setData(donor);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Donor>> findByMobile(String mobile){
        ResponseStructure<Donor> responseStructure = new ResponseStructure<>();
        Donor donor = donorDoa.findByMobile(mobile);
        if (Objects.isNull(donor)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Donor not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Donor found successfully");
            responseStructure.setData(donor);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Donor>> updateDonor(DonorRequest request, Long id){
        ResponseStructure<Donor> responseStructure = new ResponseStructure<>();
        Donor donor = donorDoa.findById(id);
        if (Objects.isNull(donor)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Donor not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        donor.setEmail(request.getEmail());
        donor.setMobile(request.getMobile());
        donor.setPassword(Aes.encrypt(request.getPassword()));
        donor.setBloodGroup(request.getBloodGroup());
        donor.setAddress(request.getAddress());
        donor.setAge(request.getAge());
        donor.setFirstName(request.getFirstName());
        donor.setLastName(request.getLastName());
        donor.setActiveDonor(request.isActiveDonor());
        donor.setAadharNo(request.getAadharNo());
        donor.setCity(request.getCity());
        donor.setState(request.getState());
        donor.setProfilPic(request.getProfilPic());
        donor.setRole("ROLE_DONOR");
        donor.setName(request.getFirstName()+" "+request.getLastName());
        donor = donorDoa.updateDonor(donor);
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Donor saved successfully");
        responseStructure.setData(donor);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Donor>>> findByCityAndSatate(String city, String state){
        ResponseStructure<List<Donor>> responseStructure=new ResponseStructure<>();
        List<Donor> donor=donorDoa.findByCityAndState(city, state);
        if(Objects.isNull(donor)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Donors not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }else{
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Donors found successfully");
            responseStructure.setData(donor);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Donor>>> findByCityAndStateAndAge(String city, String state,int age){
        ResponseStructure<List<Donor>> responseStructure =new ResponseStructure<>();
        List<Donor> donor = donorDoa.findBYCityAndStateAndAge(city, state, age);
        if(Objects.isNull(donor)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Donors not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }else{
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Donors found successfully");
            responseStructure.setData(donor);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }
}
