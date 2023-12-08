package com.record.company.com.bussines;

import com.record.company.com.domain.dto.user.*;

import java.util.List;

public interface IUserServices {

    List <UserDto> getAllUser();
    UserDto getUserById(int id);
    List <UserPurchaseDto> getPurchaseByUserId (int id);
    UserDto createUser(CreateUserDto user);
    UserDto updateUser(int id , UpdateUserDto user);
    UserDto deleteUser(int id);




}
