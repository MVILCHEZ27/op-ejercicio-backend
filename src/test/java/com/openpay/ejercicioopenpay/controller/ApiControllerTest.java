package com.openpay.ejercicioopenpay.controller;

import com.openpay.apis.marvel.mavel.model.ApisMarvel;
import com.openpay.apis.marvel.mavel.service.ApisMarvelService;
import com.openpay.ejercicioopenpay.model.ApiModel;
import com.openpay.ejercicioopenpay.repository.ApiRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    @Mock
    private ApiRepository apiRepository;

    @Mock
    private ApisMarvelService apisMarvelService;

    @Mock
    private ApisMarvel apisMarvel;

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCharacters() {

        String url = "http://gateway.marvel.com/v1/public/characters";
        String response = "{\n" +
                "    \"code\": 200,\n" +
                "    \"status\": \"Ok\",\n" +
                "    \"copyright\": \"© 2023 MARVEL\",\n" +
                "    \"attributionText\": \"Data provided by Marvel. © 2023 MARVEL\",\n" +
                "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2023 MARVEL</a>\",\n" +
                "    \"etag\": \"55342c8b21941bfea4b795ff85633d9063e1da0e\",\n"+
                "}";
        Mockito.lenient().when(apisMarvel.getUrlCharacters()).thenReturn(url);
        Mockito.lenient().when(apisMarvelService.getAllCharacters(url)).thenReturn(response);
        Mockito.lenient().when(apiRepository.findAll()).thenReturn(setApiModelInfo());
        Mockito.lenient().when(apiController.getCharacters()).thenReturn(setCharacterInfo());
        Object characters = apiController.getCharacters();
        Assertions.assertThat(characters).isEqualTo(setCharacterInfo());
    }

    @Test
    void getCharacterById(){
        String url = "http://gateway.marvel.com/v1/public/characters/";
        String id = "1011334";
        String response = "{\n" +
                "    \"code\": 200,\n" +
                "    \"status\": \"Ok\",\n" +
                "    \"copyright\": \"© 2023 MARVEL\",\n" +
                "    \"attributionText\": \"Data provided by Marvel. © 2023 MARVEL\",\n" +
                "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2023 MARVEL</a>\",\n" +
                "    \"etag\": \"55342c8b21941bfea4b795ff85633d9063e1da0e\",\n"+
                "}";
        Mockito.lenient().when(apisMarvel.getUrlCharacterById()).thenReturn(url);
        Mockito.lenient().when(apisMarvelService.getCharacterById(url, id)).thenReturn(response);
        Mockito.lenient().when(apiRepository.findAll()).thenReturn(setApiModelInfo());
        Mockito.lenient().when(apiController.getCharacterById(id)).thenReturn(setCharacterInfo());
        Object character = apiController.getCharacterById(id);
        Assertions.assertThat(character).isEqualTo(setCharacterInfo());
    }

    @Test
    void getBitacora(){
        List<ApiModel> api = setApiModelInfo();

        Mockito.lenient().when(apiRepository.findAll()).thenReturn(setApiModelInfo());
        Mockito.lenient().when(apiController.getBitacora()).thenReturn(api);
        List<ApiModel> response = apiController.getBitacora();
        Assertions.assertThat(response.get(0).getDate()).isEqualTo(api.get(0).getDate());

    }

    private String setCharacterInfo(){
        String apiResponse = "{\n" +
                "    \"code\": 200,\n" +
                "    \"status\": \"Ok\",\n" +
                "    \"copyright\": \"© 2023 MARVEL\",\n" +
                "    \"attributionText\": \"Data provided by Marvel. © 2023 MARVEL\",\n" +
                "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2023 MARVEL</a>\",\n" +
                "    \"etag\": \"55342c8b21941bfea4b795ff85633d9063e1da0e\",\n"+
                "}";
        return apiResponse;
    }

    private List<ApiModel> setApiModelInfo(){
        List<ApiModel> apiModelList = new ArrayList<>();
        ApiModel apiModel = new ApiModel();
        apiModel.setDate(new Date(2023-06-27));
        apiModelList.add(apiModel);

        return apiModelList;
    }
}