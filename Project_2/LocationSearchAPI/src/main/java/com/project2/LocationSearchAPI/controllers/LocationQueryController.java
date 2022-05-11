package com.project2.LocationSearchAPI.controllers;

import com.project2.LocationSearchAPI.entities.LocationQuery;
import com.project2.LocationSearchAPI.service.LocationQueryService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/search")
public class LocationQueryController {

    @Setter(onMethod =@__({@Autowired}))
    private LocationQueryService locationQueryService;

//    @PostMapping("/")
//    public void addLocationQuery(@RequestBody LocationQuery locationQuery) {
//        boolean success = locationQueryService.addSearch(locationQuery);
//        if (success == false) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error happened when adding a search object");
//        }
//    }

    @GetMapping("/")
    public List<LocationQuery> getAllLocationQueries() {
        return locationQueryService.getAllSearches();
    }

    @GetMapping("")
    public List<LocationQuery> getAllByFilter(@RequestParam String filter, @RequestParam String filterStr, @RequestParam int minNum) {
        List<LocationQuery> retList = locationQueryService.getAllByFilter(filter, filterStr, minNum);
        return retList;
    }
}
