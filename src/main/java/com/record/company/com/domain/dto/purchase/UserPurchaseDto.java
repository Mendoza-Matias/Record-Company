package com.record.company.com.domain.dto.purchase;

import com.record.company.com.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPurchaseDto {
    private User user;
}
