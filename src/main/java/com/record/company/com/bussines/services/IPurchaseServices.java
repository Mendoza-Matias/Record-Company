package com.record.company.com.bussines.services;

import com.record.company.com.domain.dto.purchase.AlbumPurchaseDto;
import com.record.company.com.domain.dto.purchase.CreatePurchaseDto;
import com.record.company.com.domain.dto.purchase.PurchaseDto;
import com.record.company.com.domain.dto.purchase.UserPurchaseDto;

import java.util.Date;
import java.util.List;


public interface IPurchaseServices {


    List<PurchaseDto> GetAllPrePurchase();

    PurchaseDto getPurchaseById(int id);

    PurchaseDto createPrePurchase(int idAlbum ,int idUser);

    PurchaseDto updatePrePurchase(int id , int idAlbum);

    PurchaseDto deletePrePurchase(int id);


}
