package com.Project_2_Location_Status_API.Controllers;

import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import com.Project_2_Location_Status_API.Services.StatusService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("status")
public class StatusController {

    @Setter(onMethod =@__({@Autowired}))
    private StatusService statusService;

    @GetMapping("{location}")
    public ResponseEntity getStatusByLocation(@PathVariable String location) {
        return ResponseEntity.ok(statusService.getStatusByLocation(location));
    }

    @PostMapping
    public ResponseEntity createNewStatus(@RequestBody Status status) {
        try {
            statusService.createNewStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error creating status");
        }
        return ResponseEntity.ok("Successfully created new status for " + status.getLocation());
    }
}
