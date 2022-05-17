package com.Project_2_Location_Search_API.service;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.repositories.LocationQueryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocationQueryServiceTest {
    private LocationQueryService locationQueryService;
    private LocationQueryRepository mockLocationQueryRepository;

    @BeforeEach
    public void initBefore() {
        mockLocationQueryRepository = mock(LocationQueryRepository.class);
        locationQueryService = new LocationQueryService();
        locationQueryService.setLocationQueryRepository(mockLocationQueryRepository);
    }

    @Test
    public void shouldCreateNewSearch(){
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, 20, 100);

        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);
        Assertions.assertDoesNotThrow(() -> {
            locationQueryService.addSearch(locationQuery);
        });
    }

    @Test
    public void shouldFailToCreateNewSearchLocation() {
        LocationQuery locationQuery = new LocationQuery(1, null, "unsafe",
                500, 300, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Location name can't be null", ex.getMessage(), "Wrong exception thrown when given a null location");
    }

    @Test
    public void shouldFailToCreateNewSearchStatus() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", null,
                500, 300, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Status can't be null", ex.getMessage(), "Wrong exception thrown when given a null status");
    }

    @Test
    public void shouldFailToCreateNewSearchPopulation() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                null, 300, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Population can't be null", ex.getMessage(), "Wrong exception thrown when given a null population");
    }

    @Test
    public void shouldFailToCreateNewSearchVaccinated() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, null, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Vaccinated can't be null", ex.getMessage(), "Wrong exception thrown when given a null vaccination");
    }

    @Test
    public void shouldFailToCreateNewSearchTotalInfections() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, null, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Total infections can't be null", ex.getMessage(), "Wrong exception thrown when given a null Total Infections");
    }

    @Test
    public void shouldFailToCreateNewSearchTotalDeaths() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, null, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Total deaths can't be null", ex.getMessage(), "Wrong exception thrown when given a null Total Deaths");
    }

    @Test
    public void shouldFailToCreateNewSearchTotalRecovered() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, 20, null);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.addSearch(locationQuery));
        Assertions.assertEquals("Total recovered can't be null", ex.getMessage(), "Wrong exception thrown when given a null Total Recovered");
    }

    @Test
    public void shouldGetAllSearches() {
        List<LocationQuery> locationQueries = new ArrayList<>();
        when(mockLocationQueryRepository.findAll()).thenReturn(locationQueries);

        Assertions.assertDoesNotThrow(() -> {
            locationQueryService.getAllSearches();
        });
    }

    @Test
    public void shouldGetAllByFilter() {
        List<LocationQuery> locationQueries = new ArrayList<>();
        when(mockLocationQueryRepository.findAll()).thenReturn(locationQueries);

        Assertions.assertDoesNotThrow(() -> {
            locationQueryService.getAllByFilter("location_name",    "test", 0);
            locationQueryService.getAllByFilter("status",           "test", 0);
            locationQueryService.getAllByFilter("population",       "test", 0);
            locationQueryService.getAllByFilter("vaccinated",       "test", 0);
            locationQueryService.getAllByFilter("total_infections", "test", 0);
            locationQueryService.getAllByFilter("total_deaths",     "test", 0);
            locationQueryService.getAllByFilter("total_recovered",  "test", 0);
        });
    }
}
