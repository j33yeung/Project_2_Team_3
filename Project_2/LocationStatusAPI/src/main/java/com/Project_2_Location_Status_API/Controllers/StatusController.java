package com.Project_2_Location_Status_API.Controllers;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Services.CovidApiService;
import com.Project_2_Location_Status_API.Services.StatusService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("status")
public class StatusController {

    @Setter(onMethod =@__({@Autowired}))
    private StatusService statusService;
    @Setter(onMethod =@__({@Autowired}))
    private CovidApiService covidApiService;

//    @GetMapping("{location}")
//    public ResponseEntity getStatusByLocation(@PathVariable String location) {
//        return ResponseEntity.ok(statusService.getStatusByLocation(location));
//    }

    @PostMapping
    public ResponseEntity saveNewStatus(@RequestBody Status status) {
        try {
            statusService.saveNewStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error creating status");
        }
        return ResponseEntity.ok("Successfully created new status for " + status.getLocation());
    }

    @GetMapping("{country}")
    public ResponseEntity getStatusBasedOnLocation(@PathVariable String country) {

        Status status = new Status();
        CovidStatsDTO covidStats = covidApiService.getAllDataByCountry(country).getBody();
        VaccineDataDTO vaccineStats = covidApiService.getAllVaccineDataByCountry(country).getBody();

        status.setLocation(country);
        status.setScore(statusService.calculateScore(covidStats,vaccineStats));
        status.setCreationDate(LocalDate.now());
        statusService.saveNewStatus(status);
        return ResponseEntity.ok(statusService.calculateStatusReport(status.getScore()));
    }
}
