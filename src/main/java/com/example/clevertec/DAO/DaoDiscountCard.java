package com.example.clevertec.DAO;

import com.example.clevertec.data.Discount;

import java.util.ArrayList;
import java.util.List;

public class DaoDiscountCard {
    public static List<Discount> setOfCard = new ArrayList<>();

    static {
        Discount d1 = new Discount(1l, "card-123", 5.0);
        Discount d2 = new Discount(2l, "card-124", 10.0);
        Discount d3 = new Discount(3l, "card-125", 15.0);
        Discount d4 = new Discount(4l, "card-126", 10.0);
        setOfCard.add(d1);
        setOfCard.add(d2);
        setOfCard.add(d3);
        setOfCard.add(d4);
    }
}
