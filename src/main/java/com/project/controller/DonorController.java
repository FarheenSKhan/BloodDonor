package com.project.controller;


import com.project.dto.request.DonorRequest;
import com.project.dto.request.response.GetAllDonorResponse;
import com.project.entity.Donor;
import com.project.entity.ResponseStructure;
import com.project.entity.User;
import com.project.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Donor")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping("/saveDonor")
    public ResponseEntity<ResponseStructure<Donor>> saveDonor(@RequestBody DonorRequest donor){
        return donorService.saveDonor(donor);
    }

    @DeleteMapping("/deleteUserDonor")
    public ResponseEntity<ResponseStructure<User>> deleteDonor(@RequestParam  Long id){
        return donorService.deleteDonor(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseStructure<List<GetAllDonorResponse>>> findAll(){
        return donorService.findAll();
    }

    @GetMapping("/findByBloodGroup")
    public ResponseEntity<ResponseStructure<List<Donor>>> findByBloodGroup(@RequestParam String bloodGroup){
        return donorService.findByBloodGroup(bloodGroup);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<ResponseStructure<Donor>> findByEmail(@RequestParam String email){
        return donorService.findByEmail(email);
    }

    @GetMapping("/findByMobile")
    public ResponseEntity<ResponseStructure<Donor>> findByMobile(@RequestParam String mobile){
        return donorService.findByMobile(mobile);
    }
    @PutMapping("/updateDonor")
    public ResponseEntity<ResponseStructure<Donor>> updateDonor(@RequestBody DonorRequest donor,@RequestParam Long id){
        return donorService.updateDonor(donor , id);
    }
}

