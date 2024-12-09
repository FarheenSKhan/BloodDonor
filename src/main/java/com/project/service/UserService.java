package com.project.service;

import com.project.dao.UserDao;
import com.project.dto.request.DonorRequest;
import com.project.entity.Donor;
import com.project.entity.ResponseStructure;
import com.project.entity.User;
import com.project.utill.Aes;
import com.project.utill.EmailSender;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private EmailSender emailSender;

    public ResponseEntity<ResponseStructure<Page<User>>> findAll(int page, int pageSize, String field) {
        ResponseStructure<Page<User>> responseStructure = new ResponseStructure<>();
        Page<User> mobile = userDao.findAll(page, pageSize, field);
        if (mobile == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Mobile not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("All mobile Founds");
            responseStructure.setData(mobile);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<User>> findById(Long id) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userDao.findById(id);
        if (user == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("User not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("User found");
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<User>> findByEmail(String email) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userDao.findByEmail(email);
        if (user == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("User not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("User found by email");
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<User>> loginUser(String email, String password) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userDao.findByEmail(email);
        if (user == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("User not found with email" + email);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        if (password == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("password not found with email" + email);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        if (!Aes.decrypt(user.getPassword()).equals(password)) {
            responseStructure.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseStructure.setMessage("Password is incorrect for user" + email);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.UNAUTHORIZED);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("User logged in successfully");
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<String>> forgetPassword(String email) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        User user = userDao.findByEmail(email);
        if (user == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("email does not exists" + email);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            Long otp = (long) (Math.random() * (9999 - 1000) + 1000);
            user.setOtp(otp);
            userDao.saveUser(user);
//           emailSender.sendEmail(user.getEmail(), "This is Your OTP \n" +
//                            " Don't Share OTP with Anyone\n " +
//                            "Enter this OTP To Update Password \n" + " -> OTP ",
//                    "Your OTP To Update Password" + otp);


            String emailBody = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px; max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 5px;\">" +
                    "<h2 style=\"text-align: center; color: #4CAF50;\">Password Reset Request</h2>" +
                    "<p style=\"font-size: 16px; line-height: 1.5;\">Hello,</p>" +
                    "<p style=\"font-size: 16px; line-height: 1.5;\">We received a request to reset your password. Use the OTP below to reset it:</p>" +
                    "<div style=\"text-align: center; margin: 20px 0;\">" +
                    "<span style=\"display: inline-block; font-size: 18px; background-color: #f7f7f7; padding: 10px 20px; border-radius: 5px; border: 1px solid #ccc;\">" +
                    otp +
                    "</span>" +
                    "</div>" +
                    "<p style=\"font-size: 16px; line-height: 1.5;\">If you did not request a password reset, please ignore this email or contact support if you have questions.</p>" +
                    "<p style=\"font-size: 16px; line-height: 1.5;\">Thank you,<br/>The MotoPoint Team</p>" +
                    "</div>";

            emailSender.sendEmail(user.getEmail(), "Password Reset",emailBody);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("OTP sent to email ID:" + email);
            responseStructure.setData("OTP sent to the email of user");
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<User>> validotp(Long otp) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userDao.findByOtp(otp);
        if (user != null) {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("success");
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Invalid OTP");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<User>> updatePassword(String password, Long otp) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userDao.findByOtp(otp);
        if (user == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("password cannot updated, invalid otp");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            user.setPassword(Aes.encrypt(password));
            user.setOtp(otp);
            userDao.saveUser(user);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("password updated successfully" + user.getPassword());
            responseStructure.setData(user);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }
}
