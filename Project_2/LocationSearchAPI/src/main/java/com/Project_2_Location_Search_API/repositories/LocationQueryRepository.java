package com.Project_2_Location_Search_API.repositories;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LocationQueryRepository extends JpaRepository<LocationQuery, Integer> {

//    These can be used to replace LocationName and status, or get a specific value from one of the ints
//    ArrayList<LocationQuery> findAllByLocationName(String location_name);
//    ArrayList<LocationQuery> findAllByPopulation(int population);
//    ArrayList<LocationQuery> findAllByStatus(String Status);
//    ArrayList<LocationQuery> findAllByTotalDeaths(int total_deaths);
//    ArrayList<LocationQuery> findAllByTotalRecovered(int total_recovered);
//    ArrayList<LocationQuery> findAllByTotalInfections(int total_infections);
//    ArrayList<LocationQuery> findAllByVaccinated(int vaccinated);


    @Query("FROM LocationQuery WHERE locationName = :location_name")
    List<LocationQuery> findAllByLocationName(@Param("location_name") String locationName);

    @Query("FROM LocationQuery WHERE status = :status")
    List<LocationQuery> findAllByStatus(@Param("status") String status);

    @Query("FROM LocationQuery WHERE population >= :population")
    List<LocationQuery> findAllByPopulation(@Param("population") int population);

    @Query("FROM LocationQuery WHERE vaccinated >= :vaccinated")
    List<LocationQuery> findAllByVaccinatedPopulation(@Param("vaccinated") int vaccinated);

    @Query("FROM LocationQuery WHERE totalInfections >= :total_infections")
    List<LocationQuery> findAllByTotalInfections(@Param("total_infections") int totalInfections);

    @Query("FROM LocationQuery WHERE totalDeaths >= :total_deaths")
    List<LocationQuery> findAllByTotalDeaths(@Param("total_deaths") int totalDeaths);

    @Query("FROM LocationQuery WHERE totalRecovered >= :total_recovered")
    List<LocationQuery> findAllByTotalRecovered(@Param("total_recovered") int totalRecovered);
}
