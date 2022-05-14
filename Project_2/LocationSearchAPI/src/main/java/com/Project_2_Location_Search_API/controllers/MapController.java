package com.Project_2_Location_Search_API.controllers;

import com.Project_2_Location_Search_API.service.MapService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/limitCountry")
    public ResponseEntity limitCountry(@RequestParam String q, @RequestParam String format, @RequestParam String countrycodes) {
        return ResponseEntity.ok(mapService.getByLimitCountry(q, format, countrycodes).getBody());
    }

    @GetMapping("/fromQuery")
    public ResponseEntity fromQuery(@RequestParam String q, @RequestParam String format) {
        return ResponseEntity.ok(mapService.getByQuery(q, format).getBody());
    }

    @GetMapping("/general")
    public ResponseEntity general(@RequestParam String q) {
        return ResponseEntity.ok(mapService.getGeneral(q).getBody());
    }
}
