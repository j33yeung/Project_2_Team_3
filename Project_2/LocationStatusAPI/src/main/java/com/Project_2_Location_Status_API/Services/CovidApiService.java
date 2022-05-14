package com.Project_2_Location_Status_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CovidApiService {

    private static final String GET_COVID_API = "https://disease.sh/v3/covid-19/countries/";


//    @Autowired
//    private RestTemplate covidApiRestTemplate;

    public ResponseEntity<String> getAllDataByCountry(String country){

        RestTemplate covidApiRestTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        return covidApiRestTemplate.exchange(GET_COVID_API + country, HttpMethod.GET,entity,String.class);


//      return covidApiRestTemplate.getForEntity(GET_COVID_API+ country,String.class);

    }



}
