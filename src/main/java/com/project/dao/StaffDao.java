package com.project.dao;

import com.project.entity.Staff;
import com.project.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StaffDao {

    @Autowired
    private StaffRepository staffRepository;

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }

    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff findById(Long id) {
        Optional<Staff> optional = staffRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public List<Staff> findByDepartment(String department) {
        return staffRepository.findByDepartment(department);
    }

    public Staff findByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    public Staff findByMobile(String mobile) {
        return staffRepository.findByMobile(mobile);
    }
}