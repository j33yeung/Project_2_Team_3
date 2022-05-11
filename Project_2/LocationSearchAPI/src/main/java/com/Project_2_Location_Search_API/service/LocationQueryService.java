package com.Project_2_Location_Search_API.service;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.repositories.LocationQueryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationQueryService {

    private LocationQueryRepository locationQueryRepository;

    public LocationQueryService(LocationQueryRepository locationQueryRepository) {
        super();
        this.locationQueryRepository = locationQueryRepository;
    }

    public boolean addSearch(LocationQuery locationQuery) {
        locationQueryRepository.save(locationQuery);
        return true;
    }

    public List<LocationQuery> getAllSearches() { return locationQueryRepository.findAll(); }

    public List<LocationQuery> getAllByFilter(String filter, String filterStr, int minNum) {
        List<LocationQuery> retList = new ArrayList<>();
        switch (filter.toLowerCase()) {
            case "locationname":
                retList = locationQueryRepository.findAllByLocationName(filterStr);
                break;
            case "status":
                retList = locationQueryRepository.findAllByStatus(filterStr);
                break;
            case "population":
                retList = locationQueryRepository.findAllByPopulation(minNum);
                break;
            case "vaccinated":
                retList = locationQueryRepository.findAllByVaccinatedPopulation(minNum);
                break;
            case "totalInfections":
                retList = locationQueryRepository.findAllByNumInfections(minNum);
                break;
            case "totalDeaths":
                retList = locationQueryRepository.findAllByTotalDeaths(minNum);
                break;
            case "totalRecovered":
                retList = locationQueryRepository.findAllByTotalRecovered(minNum);
                break;
        }
        return retList;
    }

}
