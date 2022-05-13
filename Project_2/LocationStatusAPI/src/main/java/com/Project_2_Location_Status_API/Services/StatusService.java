package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatusService {

    @Setter(onMethod = @__({@Autowired}))
    private StatusRepository statusRepository;

    public Status getStatusByLocation(String location) {
        Status op = statusRepository.findByLocation(location);
        if (op != null) {
            return op;
        } else {
            throw new NullPointerException(location + " could not be found");
        }
    }

    public void createNewStatus(Status status) {
        if(status.getLocation() == null || status.getLocation().isEmpty()) {
            throw new NullPointerException("Can't create a status without a location");
        } else if(status.getScore() == null || status.getScore() < 0) {
            throw new NullPointerException("Can't create a status with a null or negative score");
        } else if(status.getCreationDate() == null) {
            throw new NullPointerException("Can't create a status with a null creation date");
        } else if(status.getCreationDate().isAfter(LocalDate.now())) {
            throw new NullPointerException("Can't create a status with a date in the future");
        } else {
            statusRepository.save(status);
        }
    }
}
