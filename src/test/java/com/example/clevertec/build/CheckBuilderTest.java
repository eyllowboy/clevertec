package com.example.clevertec.build;

import com.example.clevertec.data.Check;
import com.example.clevertec.exeptions.IdNotFoundException;
import com.example.clevertec.service.DirectorCheck;
import com.example.clevertec.service.SuperMarketCheckBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckBuilderTest {


    @Test
    public void whenCreateCheckOneProduct() throws IOException {
        // given
        List<String> list = List.of("1-1");
        String card = "card-123";
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());


        // when
        Check check = directorCheck.buildCheck(list, card);

        // then
        assertEquals(check.getTotal(), 5.0);
        assertEquals(check.getDescription(), "Check from supermarket");
        assertEquals(check.getPositionProducts().size(), 1);
        assertEquals(check.getDiscountPercent(), 5);
        assertEquals(check.getTotalWithDiscount(), 4.75);

    }
    @Test
    public void whenCreateCheckTwoProduct() throws IOException {
        // given
        List<String> list = List.of("1-1","2-1","3-3");
        String card = "card-123";
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());


        // when
        Check check = directorCheck.buildCheck(list, card);

        // then
        assertEquals(check.getPositionProducts().size(), 3);
    }
    @Test
    public void whenCreateCheckIdProductNotFound() throws IOException {
        // given
        List<String> list = List.of("9999-1");
        String card = "card-123";
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());


        // when
        final Exception exception = assertThrows(Exception.class,
                () -> {directorCheck.buildCheck(list, card);});

        // then
        assertEquals(IdNotFoundException.class, exception.getClass());
        assertEquals("Goods with 9999 doesn't found", exception.getMessage());
    }
    @Test
    public void whenCreateCheckIdCardNotFound() throws IOException {
        // given
        List<String> list = List.of("1-1");
        String card = "card-999999";
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());


        // when
        final Exception exception = assertThrows(Exception.class,
                () -> {directorCheck.buildCheck(list, card);});

        // then
        assertEquals(IdNotFoundException.class, exception.getClass());
        assertEquals("Discount card card-999999 doesn't found", exception.getMessage());
    }
    @Test
    public void whenCreateCheckWithoutDiscontCard() throws IOException {
        // given
        List<String> list = List.of("1-1");
        String card = null;
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());


        // when
        Check check = directorCheck.buildCheck(list, card);

        // then
        assertEquals(check.getTotal(), check.getTotalWithDiscount());
        assertEquals(0.0,check.getDiscountPercent() );

    }
    @Test
    public void whenCreateCheckWithPromotionItemThatLessFive() throws IOException {
        // given
        List<String> list = List.of("2-4");
        String card = "card-123";
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());


        // when
        Check check = directorCheck.buildCheck(list, card);

        // then
        assertEquals(0.0, check.getPositionProducts().get(0).getDiscountPromotionGoods());


    }
    @Test
    public void whenCreateCheckWithPromotionProductThatMoreFive() throws IOException {
        // given
        List<String> list = List.of("2-6");
        String card = "card-123";
        DirectorCheck directorCheck = new DirectorCheck
                (new SuperMarketCheckBuilder());

        // when
        Check check = directorCheck.buildCheck(list, card);

        // then
        assertEquals(6,check.getPositionProducts().get(0).getDiscountPromotionGoods());

    }
}
