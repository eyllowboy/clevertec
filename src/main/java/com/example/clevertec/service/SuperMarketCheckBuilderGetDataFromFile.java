package com.example.clevertec.service;

import com.example.clevertec.data.Discount;
import com.example.clevertec.data.PositionProduct;
import com.example.clevertec.data.Product;
import com.example.clevertec.exeptions.IdNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SuperMarketCheckBuilderGetDataFromFile extends SuperMarketCheckBuilder {
    private String sourceCardData;
    private String sourceProductData;

    public SuperMarketCheckBuilderGetDataFromFile(String sourceCardData, String sourceProductData) {
        this.sourceCardData = sourceCardData;
        this.sourceProductData = sourceProductData;
    }

    @Override
    public void buildSetOfProducts(List<String> list) {

        List<Product> productList = GetDataProductFromFile();
        List<PositionProduct> positionProduct = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split("-");
            Product product = productList.stream().filter(p -> Objects.equals(p.getId(), Long.parseLong(split[0])))
                    .findFirst()
                    .orElseThrow(
                            () -> new IdNotFoundException("Goods with "+split[0]+" doesn't found"));
            Double total = product.getPrice() * Integer.parseInt(split[1]);
            positionProduct.add(new PositionProduct(Integer.parseInt(split[1]), total, product, 0.0));
        }
        check.setPositionProducts(positionProduct);

    }

    @Override
    public void buildDiscount(String discountName) {
        if (discountName != null) {
            List<Discount> listDiscounts = GetDataDiscountFromFile();
            Discount discount = listDiscounts.stream().filter(d -> (Objects.equals(d.getName(), discountName)))
                    .findFirst()
                    .orElseThrow(
                            () -> new IdNotFoundException("Discount card "+discountName+" doesn't found"));
            check.setDiscountPercent(discount.getAmount());
            check.setDiscount(check.getTotal() * discount.getAmount() / 100);
        } else {
            check.setDiscountPercent(0.0);
            check.setDiscount(0.0);
        }
    }

    public List<Product> GetDataProductFromFile() {

        FileReader fw = null;
        try {
            fw = new FileReader(sourceProductData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fw);
        List<Product> productList = new ArrayList<>();
        while (scanner.hasNext()) {
            productList.add(new Product(scanner.nextLong(), scanner.next(), Boolean.valueOf(scanner.next()), scanner.nextDouble()));
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productList;

    }

    public List<Discount> GetDataDiscountFromFile() {

        FileReader fw = null;
        try {
            fw = new FileReader(sourceCardData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fw);
        List<Discount> cardList = new ArrayList<>();
        while (scanner.hasNext()) {
            cardList.add(new Discount(scanner.nextLong(), scanner.next(), scanner.nextDouble()));
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cardList;

    }

}
