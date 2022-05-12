package com.Project_2_Location_Status_API;

import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import com.Project_2_Location_Status_API.Services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocationStatusApiApplication {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StatusService statusService;

    public static void main(String[] args) {
        SpringApplication.run(LocationStatusApiApplication.class, args);
    }

}