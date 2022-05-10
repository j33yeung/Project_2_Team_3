package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {

    @Setter(onMethod = @__({@Autowired}))
    private StatusRepository statusRepository;

    public Status getStatusByLocation(String location) {
        Optional<Status> op = statusRepository.findByLocation(location);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new NullPointerException(location + " could not be found");
        }
    }

    public void createNewStatus(Status status) {
        if(status.getLocation().isEmpty()) {
            throw new NullPointerException("Can't create a status with a null location");
        } else if(status.getScore() == null) {
            throw new NullPointerException("Can't create a status with a null score");
        } else if(status.getCreationDate().isEmpty()) {
            throw new NullPointerException("Can't create a status with a null creation date");
        } else {
            statusRepository.save(status);
        }
    }
}
