package com.record.company.com.bussines.services.servicesImpl;

import com.record.company.com.bussines.services.IPurchaseServices;
import com.record.company.com.domain.dto.purchase.CreatePurchaseDto;
import com.record.company.com.domain.dto.purchase.PurchaseDto;
import com.record.company.com.domain.entity.Album;
import com.record.company.com.domain.entity.Purchase;
import com.record.company.com.domain.entity.User;
import com.record.company.com.exception.album.AlbumException;
import com.record.company.com.exception.user.UserException;
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
        return purchaseRepository.findAll().stream().map((purchase -> modelMapper.map(purchase,PurchaseDto.class))).collect(Collectors.toList());
    }

    @Override
    public PurchaseDto getPurchaseById(int id) {
        return modelMapper.map(purchaseRepository.findById(id),PurchaseDto.class);
    }

    @Override
    public PurchaseDto createPrePurchase(int idUser, int idAlbum ){

        User user = userRepository.findById(idUser).orElseThrow(()-> new UserException("Id de usuario no encontrado"));
        Album album = albumRepository.findById(idAlbum).orElseThrow(()-> new AlbumException("Id de album no encotrado"));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setDateBooking(LocalDate.now());
        purchase.setPurchaseCode(UUID.randomUUID().toString());
        purchase.setAlbum(Collections.singletonList(album));

        return modelMapper.map(purchaseRepository.save(purchase),PurchaseDto.class);
    }

    @Override
    public PurchaseDto updatePrePurchase(int id, int idAlbum) {

        Optional<Purchase> purchaseExist = purchaseRepository.findById(id);
        Purchase newPurchase = new Purchase();
        Album newAlbum = albumRepository.findById(idAlbum).orElseThrow(()-> new AlbumException("Id de album no encontrado"));


        if(purchaseExist.isPresent()){
            newPurchase = purchaseExist.get();
            newPurchase.setAlbum(Collections.singletonList(newAlbum));
        }
        return modelMapper.map(purchaseRepository.save(newPurchase),PurchaseDto.class);
    }

    @Override
    public PurchaseDto deletePrePurchase(int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        Purchase purchasDelete = purchase.get();

        if(purchase.isPresent()){
            purchaseRepository.deleteById(id);
        }
        return modelMapper.map(purchasDelete,PurchaseDto.class);
    }

}
