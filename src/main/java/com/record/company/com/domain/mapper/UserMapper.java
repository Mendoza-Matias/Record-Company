package com.record.company.com.domain.Mapper;

import com.record.company.com.domain.dto.purchase.PurchaseDto;
import com.record.company.com.domain.dto.user.UserPurchaseDto;
import com.record.company.com.domain.entity.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper {

    public static UserPurchaseDto mapPuchaseToUserPurchaseDto(Purchase purchase){
        UserPurchaseDto userPurchaseDto = new UserPurchaseDto();
        userPurchaseDto.setPurchase(List.of(purchase));
        return userPurchaseDto;
    }

}
