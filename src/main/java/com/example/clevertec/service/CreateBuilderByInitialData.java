package com.example.clevertec.service;

import com.example.clevertec.service.CheckBuilder;
import com.example.clevertec.service.SuperMarketCheckBuilder;
import com.example.clevertec.service.SuperMarketCheckBuilderGetDataFromFile;

import java.util.Objects;

public class CreateBuilderByInitialData {

    static public CheckBuilder createBuilder(String sourceCardDataRead, String sourceProductDataRead) {
        if (!Objects.equals(sourceCardDataRead, null) && !Objects.equals(sourceProductDataRead, null)) {
            return new SuperMarketCheckBuilderGetDataFromFile(sourceCardDataRead,sourceProductDataRead);
        } else {
            return new SuperMarketCheckBuilder();
        }
    }
}
