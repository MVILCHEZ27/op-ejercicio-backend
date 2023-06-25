package com.openpay.ejercicioopenpay.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @GetMapping(value = "/characters")
    public List<Object> getCharacters(){
        List<Object> characterList = new ArrayList<>();
        String URL = "http://gateway.marvel.com/v1/public/characters?ts=1687730967&apikey=525038b6cf787bb6faf908b6c4fdd3bf&hash=69e11971d7d289422c22708fabbb8d7c";
        RestTemplate restTemplate = new RestTemplate();

        Object characters = restTemplate.getForObject(URL, Object.class);

        characterList.add(characters);

        return characterList;
    }
}
