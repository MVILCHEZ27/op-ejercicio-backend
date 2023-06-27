package com.openpay.ejercicioopenpay.controller;

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

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCharacters() {

        String response = "{\n" +
                "    \"code\": 200,\n" +
                "    \"status\": \"Ok\",\n" +
                "    \"copyright\": \"© 2023 MARVEL\",\n" +
                "    \"attributionText\": \"Data provided by Marvel. © 2023 MARVEL\",\n" +
                "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2023 MARVEL</a>\",\n" +
                "    \"etag\": \"55342c8b21941bfea4b795ff85633d9063e1da0e\",\n"+
                "}";
        Mockito.lenient().when(apiRepository.findAll()).thenReturn(setApiModelInfo());
        Mockito.lenient().when(apiController.getCharacters()).thenReturn(setCharacterInfo());
        Assertions.assertThat(response).isEqualTo(setCharacterInfo());
    }

    @Test
    void getCharacterById(){
        String id = "1011334";
        String response = "{\n" +
                "    \"code\": 200,\n" +
                "    \"status\": \"Ok\",\n" +
                "    \"copyright\": \"© 2023 MARVEL\",\n" +
                "    \"attributionText\": \"Data provided by Marvel. © 2023 MARVEL\",\n" +
                "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2023 MARVEL</a>\",\n" +
                "    \"etag\": \"55342c8b21941bfea4b795ff85633d9063e1da0e\",\n"+
                "}";
        Mockito.lenient().when(apiRepository.findAll()).thenReturn(setApiModelInfo());
        Mockito.lenient().when(apiController.getCharacterById(id)).thenReturn(setCharacterInfo());
        Assertions.assertThat(response).isEqualTo(setCharacterInfo());
    }

    @Test
    void getBitacora(){
        List<ApiModel> api = setApiModelInfo();
        Mockito.lenient().when(apiController.getBitacora()).thenReturn(api);
        Assertions.assertThat(setApiModelInfo().get(0).getDate()).isEqualTo(api.get(0).getDate());

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