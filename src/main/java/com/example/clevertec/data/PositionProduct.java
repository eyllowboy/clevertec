package com.example.clevertec.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionProduct {


    private Integer amount;

    private Double total;

    private Product products;

    private Double discountPromotionGoods;

    @Override
    public String toString() {
        return "PositionProduct{" +
                "amount=" + amount +
                ", total=" + total +
                ", discountPromotionGoods=" + discountPromotionGoods +
                ", products=" + products +
                '}';
    }
}
