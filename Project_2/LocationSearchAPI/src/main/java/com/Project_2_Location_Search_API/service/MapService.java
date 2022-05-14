package com.Project_2_Location_Search_API.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MapService {

    private final String key = "pk.4e3f27d87b71d3a12326e0641a621a8d";
    private final String baseURL = "https://api.locationiq.com/v1";

    private ResponseEntity fetchRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity getByPostalCode(String postalcode, String countrycodes, String format) {
        String url = String.format("%s/search.php?key=%s&postalcode=%s&countrycodes=%s&format=%s", baseURL, key, postalcode, countrycodes, format);
        return fetchRequest(url);
    }

    public ResponseEntity getByStructured(String street, String city, String county, String state, String country, String postalcode, String format) {
        String url = String.format("%s/search.php?key=%s&street=%s&city=%s&county=%s&state=%s&country=%s&postalcode=%s&format=%s", baseURL, key, street, city, county, state, country, postalcode, format);
        return fetchRequest(url);
    }

    public ResponseEntity getByLimitCountry(String q, String format, String countrycodes) {
        String url = String.format("%s/autocomplete.php?key=%s&q=%s&format=%s&countrycodes=%s", baseURL, key, q, format, countrycodes);
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
}
