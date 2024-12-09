package com.project.controller;

import com.project.dto.request.StaffRequest;
import com.project.entity.ResponseStructure;
import com.project.entity.Staff;
import com.project.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/saveStaff")
    public ResponseEntity<ResponseStructure<Staff>> saveStaff(@RequestBody StaffRequest staff) {
        return staffService.saveStaff(staff);
    }

    @DeleteMapping("/deleteStaff")
    public ResponseEntity<ResponseStructure<Staff>> deleteStaff(@RequestParam Long id) {
        return staffService.deleteStaff(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseStructure<List<Staff>>> findAll() {
        return staffService.findAll();
    }

    @GetMapping("/findAllDepartment")
    public ResponseEntity<ResponseStructure<List<Staff>>> findAllDepartment(String department) {
        return staffService.findByDepartment(department);
    }

    @PutMapping("/updateStaff")
    public ResponseEntity<ResponseStructure<Staff>> updateStaff(@RequestBody StaffRequest staff, @RequestParam Long id) {
        return staffService.updateStaff(staff, id);
    }
}
