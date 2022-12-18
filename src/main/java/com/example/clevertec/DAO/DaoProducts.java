package com.example.clevertec.DAO;

import com.example.clevertec.data.Product;

import java.util.ArrayList;
import java.util.List;

public class DaoProducts {
    public static List<Product> setOfProduct = new ArrayList<>();;

    static{
        Product p1 = new Product(1l, "tomato", true, 5.0);
        Product p2 = new Product(2l, "potatoes", true, 10.0);
        Product p3 = new Product(3l, "banana", false, 15.0);
        Product p4 = new Product(4l, "apple", true, 8.0);
        Product p5 = new Product(5l, "onion", false, 7.0);

        setOfProduct.add(p1);
        setOfProduct.add(p2);
        setOfProduct.add(p3);
        setOfProduct.add(p4);
        setOfProduct.add(p5);

    }
}
