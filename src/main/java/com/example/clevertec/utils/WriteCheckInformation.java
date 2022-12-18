package com.example.clevertec.utils;

import com.example.clevertec.data.Check;
import com.example.clevertec.data.PositionProduct;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCheckInformation implements SendCheckInformation{

   public void writeInformation(Check check){

       try(FileWriter writer = new FileWriter("WriteCheck.txt", false))
       {
           // запись всей строки

           writer.write("CASH RECEIPT");
           writer.append('\n');
           writer.write(check.getDescription());
           writer.append('\n');
           writer.write("DATA_AND_TIME ");
           writer.write(check.getDATETIME().toString());
           writer.append('\n');
           writer.write("AMOUNT  ");
           writer.write("DESCRIPTION  ");
           writer.write("PRICE  ");
           writer.write("PROMOTIONAL_ITEM_DISCOUNT  ");
           writer.write("TOTAL");
           writer.append('\n');
           for(PositionProduct p:check.getPositionProducts()){
               writer.write(p.getAmount().toString());
               writer.write("       ");
               writer.write(p.getProducts().getDescription());
               writer.write("        ");
               writer.write(p.getProducts().getPrice().toString());
               writer.write("                 ");
               writer.write(p.getDiscountPromotionGoods().toString());
               writer.write("          ");
               writer.write(p.getTotal().toString());
               writer.append('\n');
           }
           writer.write("TAXABLE_TOTAL ");
           writer.write(check.getTotal().toString());
           writer.append('\n');
           writer.write("DISCOUNT_%");
           writer.write(check.getDiscountPercent().toString());
           writer.write("  ");
           writer.write(check.getDiscount().toString());
           writer.append('\n');
           writer.write("TOTAL ");
           writer.write(check.getTotalWithDiscount().toString());
           writer.flush();
       }
       catch(IOException ex){

           System.out.println(ex.getMessage());
       }
   }
}
