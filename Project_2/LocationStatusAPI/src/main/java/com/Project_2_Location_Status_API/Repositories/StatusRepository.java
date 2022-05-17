package com.Project_2_Location_Status_API.Repositories;

import com.Project_2_Location_Status_API.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByLocation(String location);
}
