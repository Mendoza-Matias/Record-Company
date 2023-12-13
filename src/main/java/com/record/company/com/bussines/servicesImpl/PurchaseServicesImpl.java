package com.record.company.com.bussines.servicesImpl;

import com.record.company.com.bussines.IPurchaseServices;
import com.record.company.com.domain.dto.purchase.PurchaseDto;
import com.record.company.com.domain.dto.user.UserDto;
import com.record.company.com.domain.entity.Album;
import com.record.company.com.domain.entity.Purchase;
import com.record.company.com.domain.entity.User;
import com.record.company.com.domain.mapper.PurchaseMapper;
import com.record.company.com.exception.NotFoundException;
import com.record.company.com.persistence.repository.IAlbumRepository;
import com.record.company.com.persistence.repository.IPurchaseRepository;
import com.record.company.com.persistence.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseServicesImpl implements IPurchaseServices {

    @Autowired
    IPurchaseRepository purchaseRepository;

    @Autowired
    IAlbumRepository albumRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PurchaseDto> GetAllPrePurchase() {
        return PurchaseMapper.purchaseMapper(purchaseRepository.findAll());
    }

    @Override
    public PurchaseDto getPurchaseById(int id) {
        return modelMapper.map(purchaseRepository.findById(id),PurchaseDto.class);
    }

    @Override
    public PurchaseDto createPrePurchase(int userId, int albumId ){

        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("Id de usuario no encontrado"));
        Album album = albumRepository.findById(albumId).orElseThrow(()-> new NotFoundException("Id de album no encotrado"));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setDateBooking(LocalDate.now());
        purchase.setPurchaseCode(UUID.randomUUID().toString());
        purchase.setAlbum(album);
    
        purchaseRepository.save(purchase);

        return new PurchaseMapper().purchaseConvertDto(purchase);
    }

    @Override
    public PurchaseDto updatePrePurchase(int id,int idAlbum) {

        Optional<Purchase> purchaseExist = purchaseRepository.findById(id);
        Purchase newPurchase = new Purchase();
        Album newAlbum = albumRepository.findById(idAlbum).orElseThrow(()-> new NotFoundException("Id de album no encontrado"));


        if(purchaseExist.isPresent()){
            newPurchase = purchaseExist.get();
            newPurchase.setAlbum(newAlbum);
        }
        return modelMapper.map(purchaseRepository.save(newPurchase),PurchaseDto.class);
    }

    @Override
    public PurchaseDto deletePrePurchase(int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        Purchase purchasDelete = new Purchase();

        if(purchase.isPresent()){
            purchasDelete = purchase.get();
            purchaseRepository.deleteById(id);
        }
        return modelMapper.map(purchasDelete,PurchaseDto.class);
    }

    @Override
    public UserDto getUserByPurchaseCode(String purchaseCode) {
        return new PurchaseMapper().purchaseUserDto(purchaseRepository.getUserByPurchaseCode(purchaseCode));
    }

}
