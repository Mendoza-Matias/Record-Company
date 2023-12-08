package com.record.company.com.bussines.servicesImpl;


import com.record.company.com.bussines.IUserServices;

import com.record.company.com.domain.dto.user.CreateUserDto;
import com.record.company.com.domain.dto.user.UpdateUserDto;
import com.record.company.com.domain.dto.user.UserDto;
import com.record.company.com.domain.dto.user.UserPurchaseDto;
import com.record.company.com.domain.entity.User;
import com.record.company.com.exception.NotFoundException;
import com.record.company.com.persistence.repository.IPurchaseRepository;
import com.record.company.com.persistence.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Id del usuario no encontrado"));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto createUser(CreateUserDto user) {

        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRol(user.getRol());

        return modelMapper.map(userRepository.save(userEntity),UserDto.class);
    }

    @Override
    public List<UserPurchaseDto> getPurchaseByUserId(int id) {
        return purchaseRepository.getPurchaseByUserId(id).stream().map(purchase -> modelMapper.map(purchase,UserPurchaseDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(int id, UpdateUserDto user) {

       Optional<User> userExist = userRepository.findById(id);
       User newUser = new User();

       if(userExist.isPresent()){

           newUser = userExist.get();
           newUser.setName(user.getName());
           newUser.setEmail(user.getEmail());
           newUser.setPassword(user.getPassword());

       }
        return modelMapper.map(userRepository.save(newUser),UserDto.class) ;
    }

    @Override
    public UserDto deleteUser(int id) {

        Optional<User> user = userRepository.findById(id);
        User userDelete = new User();

        if(user.isPresent()){
            userDelete = user.get();
            userRepository.deleteById(id);
        }

        return modelMapper.map(userDelete,UserDto.class) ;
    }


}
