package com.Project_2_Location_Search_API.service;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.entities.StatusReport;
import com.Project_2_Location_Search_API.repositories.LocationQueryRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationQueryService {

    @Setter(onMethod = @__({@Autowired}))
    private LocationQueryRepository locationQueryRepository;

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

//    This code is now redundant but I will leave it just in case                   //
//    public LocationQueryService(LocationQueryRepository locationQueryRepository) {
//        super();
//        this.locationQueryRepository = locationQueryRepository;
//    }

    public LocationQuery saveSearch(LocationQuery locationQuery) {
        if(locationQuery.getLocationName() == null || locationQuery.getLocationName().isEmpty()) {
            throw new NullPointerException("Location name can't be null");
        } else if(locationQuery.getStatus() == null || locationQuery.getStatus().isEmpty()) {
            throw new NullPointerException("Status can't be null");
        } else if(locationQuery.getPopulation() == null) {
            throw new NullPointerException("Population can't be null");
        } else if(locationQuery.getVaccinated() == null) {
            throw new NullPointerException("Vaccinated can't be null");
        } else if(locationQuery.getTotalInfections() == null) {
            throw new NullPointerException("Total infections can't be null");
        } else if(locationQuery.getTotalDeaths() == null) {
            throw new NullPointerException("Total deaths can't be null");
        } else if(locationQuery.getTotalRecovered() == null) {
            throw new NullPointerException("Total recovered can't be null");
        } else {
            return locationQueryRepository.save(locationQuery);
        }
    }

    public List<LocationQuery> getAllSearches() { return locationQueryRepository.findAll(); }

    public List<LocationQuery> getAllByFilter(String filter, String filterStr, int minNum) {
        List<LocationQuery> retList = new ArrayList<>();
        switch (filter.toLowerCase()) {
            case "location_name":
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
            case "total_infections":
                retList = locationQueryRepository.findAllByTotalInfections(minNum);
                break;
            case "total_deaths":
                retList = locationQueryRepository.findAllByTotalDeaths(minNum);
                break;
            case "total_recovered":
                retList = locationQueryRepository.findAllByTotalRecovered(minNum);
                break;
        }
        return retList;
    }

    public StatusReport requestStatusReportByCountry(String country){
        StatusReport statusReport = restTemplate.getForObject("http://localhost:8000/status/"+ country, StatusReport.class);
        return new StatusReport(statusReport.getId(),statusReport.getScore(),statusReport.getLocation(),statusReport.getCreationDate(),statusReport.getStatus());
    }
}
