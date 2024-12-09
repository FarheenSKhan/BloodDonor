package com.project.controller;

import com.project.dto.request.AdminRequest;
import com.project.dto.request.StaffRequest;
import com.project.entity.Admin;
import com.project.entity.ResponseStructure;
import com.project.entity.Staff;
import com.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/saveAdmin")
    public ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminRequest request){
        return adminService.saveAdmin(request);
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseStructure<List<Admin>>> findAll(){
        return adminService.findAll();
    }
    @GetMapping("/findById")
    public ResponseEntity<ResponseStructure<Admin>> findById(@RequestParam Long id){
        return adminService.findById(id);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<ResponseStructure<Admin>> findByEmail(@RequestParam String email){
        return adminService.findByEmail(email);
    }

    @GetMapping("/findByMobile")
    public ResponseEntity<ResponseStructure<Admin>> findByMobile(@RequestParam String mobile){
        return adminService.findByMobile(mobile);
    }


    @PutMapping("/updateAdmin")
    public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody AdminRequest admin, @RequestParam Long id) {
        return adminService.updateAdmin(admin, id);
    }
}
