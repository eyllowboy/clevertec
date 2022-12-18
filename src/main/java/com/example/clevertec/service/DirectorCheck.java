package com.example.clevertec.service;

import com.example.clevertec.data.Check;
import com.example.clevertec.service.CheckBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
public class DirectorCheck {
    @Autowired
   private CheckBuilder checkBuilder;


    public DirectorCheck(CheckBuilder checkBuilder) {
        this.checkBuilder = checkBuilder;
    }

    public Check buildCheck(List<String> idAndAmountProduct, String DiscountCard) throws IOException {
        checkBuilder.createCheck();
        checkBuilder.buildDescription();
        checkBuilder.buildDate();
        checkBuilder.buildSetOfProducts(idAndAmountProduct);
        checkBuilder.checkCalculatePromotionGoodsThatMoreFive();
        checkBuilder.buildTotal();
        checkBuilder.buildDiscount(DiscountCard);
        checkBuilder.buildTotalWithDiscount();
        Check check = checkBuilder.getCheck();
        return check;
    }

}
