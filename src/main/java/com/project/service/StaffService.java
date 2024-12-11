package com.project.service;

import com.project.dao.StaffDao;
import com.project.dto.request.StaffRequest;
import com.project.entity.ResponseStructure;
import com.project.entity.Staff;
import com.project.utill.Aes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StaffService {

    @Autowired
    private StaffDao staffDao;


    public ResponseEntity<ResponseStructure<Staff>> saveStaff(StaffRequest request) {
        ResponseStructure<Staff> responseStructure = new ResponseStructure<>();
        if (staffDao.findByEmail(request.getEmail()) != null) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Email already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
        if (staffDao.findByMobile(request.getMobile()) != null) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Mobile number already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        } else {
            Staff staff = new Staff();
            staff.setFirstName(request.getFirstName());
            staff.setLastName(request.getLastName());
            staff.setDepartment(request.getDepartment());
            staff.setEmail(request.getEmail());
            staff.setMobile(request.getMobile());
            staff.setPassword(Aes.encrypt(request.getPassword()));
            staff.setAadharNo(request.getAadharNo());
            staff.setProfilPic(request.getProfilPic());
            staff.setAddress(request.getAddress());
            staff.setCity(request.getCity());
            staff.setState(request.getState());
            staff.setRole("ROLE_STAFF");
            staff.setName((request.getFirstName() + " " + request.getLastName()).toUpperCase());
            staff = staffDao.saveStaff(staff);

            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Staff saved successfully");
            responseStructure.setData(staff);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);

        }
    }

    public ResponseEntity<ResponseStructure<Staff>>  deleteStaff(Long id){
        ResponseStructure<Staff> responseStructure=new ResponseStructure<>();
        Staff staff=staffDao.findById(id);
        if(Objects.isNull(staff)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Staff not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }else{
            staffDao.deleteStaff(staff);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Staff deleted successfully");
            responseStructure.setData(staff);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Page<Staff>>> findAll(int page,int pageSize,String field){
        ResponseStructure<Page<Staff>> responseStructure=new ResponseStructure<>();
        Page<Staff> staffList=staffDao.findAll(page,pageSize,field);
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Staffs found successfully");
        responseStructure.setData(staffList);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<Staff>> updateStaff(StaffRequest request,Long id){
        ResponseStructure<Staff> responseStructure=new ResponseStructure<>();
        Staff staff=staffDao.findById(id);
        if(Objects.isNull(staff)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Staff not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }else{
            staff.setFirstName(request.getFirstName());
            staff.setLastName(request.getLastName());
            staff.setDepartment(request.getDepartment());
            staff.setEmail(request.getEmail());
            staff.setMobile(request.getMobile());
            staff.setProfilPic(request.getProfilPic());
            staff.setAddress(request.getAddress());
            staff.setCity(request.getCity());
            staff.setRole("ROLE_STAFF");
            staff.setName(request.getFirstName()+" "+request.getLastName());
            staff=staffDao.updateStaff(staff);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Staff updated successfully");
            responseStructure.setData(staff);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Staff>>> findByDepartment(String department){
        ResponseStructure<List<Staff>> responseStructure=new ResponseStructure<>();
       List<Staff> staff=staffDao.findByDepartment(department);
        if(Objects.isNull(staff)){
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Staff not found for this department");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }else{
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Staff found successfully");
            responseStructure.setData(staff);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

}
