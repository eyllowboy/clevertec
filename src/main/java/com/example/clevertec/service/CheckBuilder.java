package com.example.clevertec.service;

import com.example.clevertec.data.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
public abstract class CheckBuilder {

    Check check;

    public void createCheck() {
        check = new Check();
    }

    public abstract void buildSetOfProducts(List<String> list) throws IOException;

    public abstract void buildDescription();

    public abstract void buildDiscount(String DiscountName) throws IOException;

    public abstract void buildDate();


    public abstract void buildTotal();

    public abstract void buildTotalWithDiscount();

    public abstract void checkCalculatePromotionGoodsThatMoreFive();

    public Check getCheck() {
        return check;
    }

}
