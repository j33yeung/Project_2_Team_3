package com.Project_2_Location_Search_API.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MapService {

    private final String key = "pk.4e3f27d87b71d3a12326e0641a621a8d";
    private final String baseURL = "https://api.locationiq.com/v1";
    private final String mapBaseURL = "https://maps.locationiq.com/v3";

    private ResponseEntity fetchRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    private ResponseEntity fetchImage(String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<byte[]> response = null;
        try {
            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
            response = restTemplate.getForEntity(url, byte[].class);
            return response;
        } catch( HttpServerErrorException hse ){
            throw hse;
        }
    }

    public ResponseEntity getByPostalCode(String postalcode, String countrycodes, String format) {
        String url = String.format("%s/search.php?key=%s&postalcode=%s&countrycodes=%s&format=%s", baseURL, key, postalcode, countrycodes, format);
        return fetchRequest(url);
    }

    public ResponseEntity getByStructured(String street, String city, String county, String state, String country, String postalcode, String format) {
        String url = String.format("%s/search.php?key=%s&street=%s&city=%s&county=%s&state=%s&country=%s&postalcode=%s&format=%s", baseURL, key, street, city, county, state, country, postalcode, format);
        return fetchRequest(url);
    }

    public ResponseEntity getByLimitCountryState(String state, String format, String countrycodes) {
        String[] listOfUsStates = new String[] {"alabama", "alaska", "arizona", "arkansas", "california", "colorado", "connecticut", "delaware", "florida", "georgia", "hawaii", "idaho", "illinois", "indiana", "iowa", "kansas", "kentucky", "louisiana", "maine", "maryland", "massachusetts", "michigan", "minnesota", "mississippi", "missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey", "new mexico", "new york", "north carolina", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", "south carolina", "south dakota", "tennessee", "texas", "utah", "vermont", "virginia", "washington", "west virginia", "wisconsin", "wyoming"};
        if (!Arrays.asList(listOfUsStates).contains(state.toLowerCase())) {
            throw new UnsupportedOperationException("US states only");
        }
        String url = String.format("%s/autocomplete.php?key=%s&q=%s&format=%s&countrycodes=%s", baseURL, key, state, format, countrycodes);
        return fetchRequest(url);
    }

    public ResponseEntity getByQuery(String q, String format) {
        String url = String.format("%s/autocomplete.php?key=%s&q=%s&format=%s", baseURL, key, q, format);
        return fetchRequest(url);
    }

    public ResponseEntity getGeneral(String q) {
        String url = String.format("%s/autocomplete.php?key=%s&q=%s", baseURL, key, q);
        return fetchRequest(url);
    }

    public  ResponseEntity getMap(String state, String format, String countrycodes) {
        ResponseEntity response = getByLimitCountryState(state, format, countrycodes);
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(response.getBody().toString());
            Object first = jsonArray.get(0);
            String latitude = "";
            String longitude = "";
            String[] jsonParts = first.toString().split(",");
            for (int i=0; i < jsonParts.length; i++) {
                String curr = jsonParts[i];
                if (curr.contains("lat")) {
                    latitude = curr.split(":")[1];
                } else if (curr.contains("lon")) {
                    longitude = curr.split(":")[1];
                }
            }
            latitude = latitude.replaceAll("[^a-zA-Z0-9.-]", "");
            //System.out.println(latitude);
            longitude = longitude.replaceAll("[^a-zA-Z0-9.-]", "");
            //System.out.println(longitude);
            String center = String.format("%s,%s", latitude, longitude);
            //System.out.println(center);
            String marker = String.format("icon:large-red-cutout|%s", center);
            String url = String.format("%s/staticmap?key=%s&center=%s&zoom=4&size=480x480&markers=%s", mapBaseURL, key, center, marker);
            //System.out.println(url);
            return fetchImage(url);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
