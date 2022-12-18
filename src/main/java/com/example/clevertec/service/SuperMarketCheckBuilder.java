package com.example.clevertec.service;

import com.example.clevertec.DAO.DaoDiscountCard;
import com.example.clevertec.DAO.DaoProducts;
import com.example.clevertec.data.Discount;
import com.example.clevertec.data.PositionProduct;
import com.example.clevertec.data.Product;
import com.example.clevertec.exeptions.IdNotFoundException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuperMarketCheckBuilder extends CheckBuilder {

    @Override
    public void buildSetOfProducts(List<String> list) throws IOException {
        List<PositionProduct> positionProduct = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split("-");
            Product product = DaoProducts.setOfProduct.stream().filter(p -> p.getId() == Long.parseLong(split[0]))
                    .findFirst()
                    .orElseThrow(
                            () -> new IdNotFoundException("Goods with " + split[0] + " doesn't found"));
            Double total = product.getPrice() * Integer.parseInt(split[1]);
            positionProduct.add(new PositionProduct(Integer.parseInt(split[1]), total, product, 0.0));
        }
        check.setPositionProducts(positionProduct);
    }

    @Override
    public void buildDescription() {
        check.setDescription("Check from supermarket");
    }

    @Override
    public void buildDate() {
        check.setDATETIME(LocalDateTime.now());
    }

    @Override
    public void buildTotal() {
        double sum = check.getPositionProducts().stream().mapToDouble(set -> set.getTotal()).sum();
        check.setTotal(sum);
    }

    @Override
    public void checkCalculatePromotionGoodsThatMoreFive() {
        List<PositionProduct> positionProducts = check.getPositionProducts();
        for (PositionProduct set : positionProducts) {
            if (set.getAmount() > 5 && set.getProducts().getAction()) {
                double promotion = set.getTotal() / 10;
                set.setDiscountPromotionGoods(promotion);
                set.setTotal(set.getTotal() - promotion);
            }
        }
    }

    @Override
    public void buildDiscount(String discountName) throws IOException {
        if (discountName != null) {
            Discount discount = DaoDiscountCard.setOfCard.stream().filter(d -> (Objects.equals(d.getName(), discountName)))
                    .findFirst()
                    .orElseThrow(
                            () -> new IdNotFoundException("Discount card " + discountName + " doesn't found"));
            check.setDiscountPercent(discount.getAmount());
            check.setDiscount(check.getTotal() * discount.getAmount() / 100);
        } else {
            check.setDiscountPercent(0.0);
            check.setDiscount(0.0);
        }
    }

    @Override
    public void buildTotalWithDiscount() {

        check.setTotalWithDiscount(check.getTotal() - check.getDiscount());
    }
}
