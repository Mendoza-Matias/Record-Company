package com.record.company.com.bussines;

import com.record.company.com.domain.dto.purchase.PurchaseDto;
import com.record.company.com.domain.dto.user.UserDto;

import java.util.List;


public interface IPurchaseServices {


    List<PurchaseDto> GetAllPrePurchase();

    PurchaseDto getPurchaseById(int id);

    PurchaseDto createPrePurchase(int idAlbum ,int idUser);

    PurchaseDto updatePrePurchase(int id , int idAlbum);

    PurchaseDto deletePrePurchase(int id);

    UserDto getUserByPurchaseCode(String purchaseCode);
}
