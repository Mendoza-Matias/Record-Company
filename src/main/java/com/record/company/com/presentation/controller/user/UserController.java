package com.record.company.com.presentation.controller.user;

import com.record.company.com.bussines.services.IUserServices;
import com.record.company.com.domain.dto.user.CreateUserDto;
import com.record.company.com.domain.dto.user.UpdateUserDto;
import com.record.company.com.domain.dto.user.UserDto;
import com.record.company.com.domain.dto.user.UserPurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recordCompany")
public class UserController {

    @Autowired
    IUserServices userServices;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userServices.getAllUser());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id){
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @GetMapping("{id}/purchases")
    public List<UserPurchaseDto> getPurchaseByUserId (@PathVariable("id") int id){
        return userServices.getPurchaseByUserId(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser (@RequestBody CreateUserDto user){
        return ResponseEntity.created(URI.create("/api/v1/recordCompany")).body(userServices.createUser(user));
    }


    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserDto user){
        return ResponseEntity.ok(userServices.updateUser(id,user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") int id){
        return ResponseEntity.ok(userServices.deleteUser(id));
    }
}
