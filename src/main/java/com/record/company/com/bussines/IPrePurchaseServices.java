package com.record.company.com.bussines;


import com.record.company.com.domain.dto.purchase.PurchaseDto;

import java.util.List;

public interface IPrePurchaseServices {


    List<PurchaseDto> GetAllPrePurchase();

    PurchaseDto getPrePurchaseById(int id);

    PurchaseDto createPrePurchase(int userId , int albumId);

    PurchaseDto updatePrePurchase(int albumId);

    PurchaseDto deletePrePurchase(int id);


}
