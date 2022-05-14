package com.Project_2_Location_Status_API.Controllers;

import com.Project_2_Location_Status_API.Entities.CovidStats;
import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Services.CovidApiService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/covid-api")
public class CovidApiController {



    @Setter(onMethod = @__({@Autowired}))
    private CovidApiService covidApiService;

    @GetMapping("search") // covid-api/search?country=nameOfCountry
    public ResponseEntity<CovidStats> getDataByCountry(@RequestParam String country){
        return ResponseEntity.ok(covidApiService.getAllDataByCountry(country).getBody());
    }

    @GetMapping("calculate")
    public ResponseEntity<String> getStatusBasedOnLocation(@RequestParam String country) {

        ResponseEntity<CovidStats> response = covidApiService.getAllDataByCountry(country);
        Integer score = covidApiService.calculateStatus(response);

        if (score < 50) {
            return ResponseEntity.ok().body("This locations is currently unsafe to visit.");
        }
        return ResponseEntity.ok().body("This location is currently safe to visit.");
    }


}
