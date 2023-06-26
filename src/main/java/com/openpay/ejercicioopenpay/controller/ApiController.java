package com.openpay.ejercicioopenpay.controller;


import com.openpay.ejercicioopenpay.model.ApiModel;
import com.openpay.ejercicioopenpay.repository.ApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Value("${public.key}")
    private String publicKey;

    @Value("${private.key}")
    private String privateKey;

    @Value("${timeStamp.parameter}")
    private String timeStampParameter;

    @Autowired
    private ApiRepository apiRepository;

    @GetMapping(value = "/characters")
    public List<Object> getCharacters(){

        List<Object> characterList = new ArrayList<>();
        String URL = "http://gateway.marvel.com/v1/public/characters?ts="+timeStampParameter+"&apikey="+publicKey+"&hash="+privateKey;
        RestTemplate restTemplate = new RestTemplate();

        Object characters = restTemplate.getForObject(URL, Object.class);

        characterList.add(characters);

        ApiModel apiModel = setModelInfo();
        apiRepository.save(apiModel);

        log.info("DATE/FECHA: "+ apiModel.getDate());
        log.info("ID LONG: "+ apiModel.getId());

        return characterList;
    }

    @GetMapping(value = "/character/{id}")
    public Object getCharacterById(@RequestParam String id){

        Object characterByID;
        String URL = "http://gateway.marvel.com/v1/public/characters/"+id+"?ts="+timeStampParameter+"&apikey="+publicKey+"&hash="+privateKey;
        RestTemplate restTemplate = new RestTemplate();
        characterByID = restTemplate.getForObject(URL, Object.class);

        ApiModel apiModel = setModelInfo();
        apiRepository.save(apiModel);

        log.info("DATE/FECHA: "+ apiModel.getDate());
        log.info("ID LONG: "+ apiModel.getId());

        return characterByID;
    }

    private ApiModel setModelInfo(){

        ApiModel apiModel = new ApiModel();
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        apiModel.setDate(timeStamp);

        return apiModel;
    }

}
