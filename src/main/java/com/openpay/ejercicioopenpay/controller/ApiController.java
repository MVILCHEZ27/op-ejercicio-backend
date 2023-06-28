package com.openpay.ejercicioopenpay.controller;


import com.openpay.apis.marvel.mavel.model.ApisMarvel;
import com.openpay.apis.marvel.mavel.service.ApisMarvelService;
import com.openpay.ejercicioopenpay.model.ApiModel;
import com.openpay.ejercicioopenpay.repository.ApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    private static final String PUBLIC_KEY = "525038b6cf787bb6faf908b6c4fdd3bf";
    private static final String PRIVATE_KEY = "69e11971d7d289422c22708fabbb8d7c";
    private static final String TIME_STAMP = "1687730967";

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ApisMarvel apisMarvel;

    @Autowired
    private ApisMarvelService apisMarvelService;

    @GetMapping(value = "/characters")
    public Object getCharacters(){

        String url = apisMarvel.getUrlCharacters();

        Object characters = apisMarvelService.getAllCharacters(url);

        setRepositoryInfo();

        return characters;
    }

    @GetMapping(value = "/character", params = "id")
    public Object getCharacterById(@RequestParam String id){

        String url = apisMarvel.getUrlCharacterById();

        Object characterByID = apisMarvelService.getCharacterById(url, id);

        setRepositoryInfo();

        return characterByID;
    }

    @GetMapping(value = "/bitacora")
    public List<ApiModel> getBitacora(){
        List<ApiModel> apiModelList;
        apiModelList = apiRepository.findAll();
        return apiModelList;
    }

    private ApiModel setModelInfo(){

        ApiModel apiModel = new ApiModel();
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        apiModel.setDate(timeStamp);

        return apiModel;
    }

    private void setRepositoryInfo(){

        ApiModel apiModel = setModelInfo();
        apiRepository.save(apiModel);

        log.info("DATE/FECHA: "+ apiModel.getDate());
        log.info("ID LONG: "+ apiModel.getId());
    }

}
