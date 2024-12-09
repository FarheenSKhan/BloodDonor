package com.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donor extends User {

    {
        super.setRole("ROLE_DONOR");
    }


    private String firstName;

    private String lastName;
    private String bloodGroup;
    private boolean activeDonor;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean isActiveDonor() {
        return activeDonor;
    }

    public void setActiveDonor(boolean activeDonor) {
        this.activeDonor = activeDonor;
    }
}