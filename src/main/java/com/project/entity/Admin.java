package com.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Admin extends User {
    {
        super.setRole("ROLE_ADMIN");
    }

    private String firstName;

    private String lastName;

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
}

