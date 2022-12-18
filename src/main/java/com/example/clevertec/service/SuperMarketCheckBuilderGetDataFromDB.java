package com.example.clevertec.service;

import com.example.clevertec.data.Discount;
import com.example.clevertec.data.PositionProduct;
import com.example.clevertec.data.Product;
import com.example.clevertec.exeptions.IdNotFoundException;
import com.example.clevertec.repository.CardRepository;
import com.example.clevertec.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class SuperMarketCheckBuilderGetDataFromDB extends SuperMarketCheckBuilder {
    CardRepository cardRepository;
    ProductRepository productRepository;

    @Autowired
    public SuperMarketCheckBuilderGetDataFromDB(CardRepository cardRepository, ProductRepository productRepository) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void buildSetOfProducts(List<String> list) {


        List<PositionProduct> positionProduct = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split("-");
            Product product = productRepository.findById(Long.parseLong(split[0])).orElseThrow(
                    () -> new IdNotFoundException("Goods with " + split[0] + " doesn't found"));
            Double total = product.getPrice() * Integer.parseInt(split[1]);
            positionProduct.add(new PositionProduct(Integer.parseInt(split[1]), total, product, 0.0));
        }
        check.setPositionProducts(positionProduct);

    }

    @Override
    public void buildDiscount(String discountName) {
        if (discountName != null) {
            Discount discount = cardRepository.findDiscountByName(discountName).orElseThrow(
                    () -> new IdNotFoundException("Discount card " + discountName + " doesn't found"));

            check.setDiscountPercent(discount.getAmount());
            check.setDiscount(check.getTotal() * discount.getAmount() / 100);
        } else {
            check.setDiscountPercent(0.0);
            check.setDiscount(0.0);
        }
    }
}
