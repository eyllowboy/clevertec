package com.example.clevertec;

import com.example.clevertec.service.CheckBuilder;
import com.example.clevertec.service.CreateBuilderByInitialData;
import com.example.clevertec.service.DirectorCheck;
import com.example.clevertec.data.Check;
import com.example.clevertec.utils.StrategyCheckInformation;
import com.example.clevertec.utils.WriteCheckInformation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ClevertecApplication {



    public static void main(String[] args) throws IOException {
        SpringApplication.run(ClevertecApplication.class, args);


        String cardName = null;
        String sourceCardDataRead = null;
        String sourceProductDataRead = null;
        List<String> idAndAmountProduct = new ArrayList<>();

        for (String s:args){
            if(s.startsWith("card-")){
                cardName=s;
                continue;
            }
            if(s.equals("product.txt")){
                sourceProductDataRead=s;
                continue;
            }
             if(s.equals("card.txt")){
                sourceCardDataRead=s;
                continue;
            }
            idAndAmountProduct.add(s);
        }

        // choose builder by input date
        CheckBuilder builder = CreateBuilderByInitialData.createBuilder(sourceCardDataRead,sourceProductDataRead);
        // build check
        DirectorCheck directorCheck = new DirectorCheck(builder);
        Check check = directorCheck.buildCheck(idAndAmountProduct,cardName);
        System.out.println(check.toString());
        // write check into file
        StrategyCheckInformation checkInformation = new StrategyCheckInformation(check);
        checkInformation.setSendCheckInformation(new WriteCheckInformation());
        checkInformation.executeSendCheckInformation();


    }





}


