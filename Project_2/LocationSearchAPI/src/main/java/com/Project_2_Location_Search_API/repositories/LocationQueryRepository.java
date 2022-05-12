package com.Project_2_Location_Search_API.repositories;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationQueryRepository extends JpaRepository<LocationQuery, Integer> {
    @Query("FROM LocationQuery WHERE locationName = :locationName")
    List<LocationQuery> findAllByLocationName(@Param("locationName") String locationName);
    @Query("FROM LocationQuery WHERE status = :status")
    List<LocationQuery> findAllByStatus(@Param("status") String status);
    @Query("FROM LocationQuery WHERE population >= :population")
    List<LocationQuery> findAllByPopulation(@Param("population") int population);
    @Query("FROM LocationQuery WHERE vaccinated >= :vaccinated")
    List<LocationQuery> findAllByVaccinatedPopulation(@Param("vaccinated") int vaccinated);
    @Query("FROM LocationQuery WHERE totalInfections >= :totalInfections")
    List<LocationQuery> findAllByNumInfections(@Param("totalInfections") int totalInfections);
    @Query("FROM LocationQuery WHERE totalDeaths >= :totalDeaths")
    List<LocationQuery> findAllByTotalDeaths(@Param("totalDeaths") int totalDeaths);
    @Query("FROM LocationQuery WHERE totalRecovered >= :totalRecovered")
    List<LocationQuery> findAllByTotalRecovered(@Param("totalRecovered") int totalRecovered);
}
