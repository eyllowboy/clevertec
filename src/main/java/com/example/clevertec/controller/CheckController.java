package com.example.clevertec.controller;

import com.example.clevertec.service.DirectorCheck;
import com.example.clevertec.data.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CheckController {
    @Autowired
    private DirectorCheck directorCheck;

    @GetMapping("/check")
    public Check getCategoryById( String id,String count,String card) throws IOException {

        List<String> products = List.of(id+"-"+count);
        return directorCheck.buildCheck(products, card);
    }
}
