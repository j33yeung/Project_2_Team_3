package com.Project_2_Location_Search_API.controllers;

import com.Project_2_Location_Search_API.dto.MapRequestDTO;
import com.Project_2_Location_Search_API.service.MapService;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapController {

    @Setter(onMethod =@__({@Autowired}))
    private MapService mapService;

    @GetMapping("/postal")
    public ResponseEntity postalCodeQuery(@RequestParam String postalcode, @RequestParam String countrycodes, @RequestParam String format) {
        return ResponseEntity.ok(mapService.getByPostalCode(postalcode, countrycodes, format).getBody());
    }

    @GetMapping("/structured")
    public ResponseEntity structuredQuery(@RequestParam String street, @RequestParam String city, @RequestParam String county, @RequestParam String state, @RequestParam String country, @RequestParam String postalcode, @RequestParam String format) {
        return ResponseEntity.ok(mapService.getByStructured(street, city, county, state, country, postalcode, format).getBody());
    }

    @GetMapping("/limitCountryState")
    public ResponseEntity limitCountryState(@RequestParam String state, @RequestParam String format, @RequestParam String countrycodes) {
        Object places = mapService.getByLimitCountryState(state, format, countrycodes).getBody();
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(places.toString());
            return ResponseEntity.ok(jsonArray.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/fromQuery")
    public ResponseEntity fromQuery(@RequestParam String q, @RequestParam String format) {
        return ResponseEntity.ok(mapService.getByQuery(q, format).getBody());
    }

    @GetMapping("/general")
    public ResponseEntity general(@RequestParam String q) {
        return ResponseEntity.ok(mapService.getGeneral(q).getBody());
    }

    @PostMapping("/showmap")
    public ResponseEntity showMap(@RequestBody MapRequestDTO mapRequestDTO) {
        return mapService.getMap(mapRequestDTO.getState(), mapRequestDTO.getFormat(), mapRequestDTO.getCountrycodes());
    }
}
