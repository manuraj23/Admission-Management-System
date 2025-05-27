package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Entity.User;
import com.AdmissionManagementSystem.Service.UserDetailServiceImpl;
import com.AdmissionManagementSystem.Service.UserService;
import com.AdmissionManagementSystem.Utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;


    @PostMapping("signup")
    public void signup(@RequestBody User user){
        userService.saveNewStudent(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            String username = user.getUsername();
            String password = user.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            String jwt=jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch(Exception e){
            log.error("Error during login: {}", e.getMessage());
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }


}
