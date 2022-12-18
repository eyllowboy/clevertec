package com.example.clevertec.data;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Check {

   private String Description;

    private Double Total;

    private Double TotalWithDiscount;

    private Double Discount;

    private Double DiscountPercent;

    private LocalDateTime DATETIME;

    private List<PositionProduct> positionProducts;

    @Override
    public String toString() {
        return "Check{" +
                "Description='" + Description + '\'' +
                ", Total=" + Total +
                ", TotalWithDiscount=" + TotalWithDiscount +
                ", Discount=" + Discount +
                ", TIME=" + DATETIME +
                ", positionProducts=" + positionProducts +
                '}';
    }
}