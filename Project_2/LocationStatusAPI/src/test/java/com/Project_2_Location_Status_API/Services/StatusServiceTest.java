package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatusServiceTest {
    private StatusService statusServiceService;
    private StatusRepository mockStatusRepository;

    @BeforeEach
    public void initBefore() {
        mockStatusRepository = mock(StatusRepository.class);
        statusServiceService = new StatusService();
        statusServiceService.setStatusRepository(mockStatusRepository);
    }

    @Test
    public void shouldCreateNewSearchSuccessfully(){
        Status status = new Status(1, 54, "Spain",
                LocalDate.parse("2020-01-01"));
        when(mockStatusRepository.save(status)).thenReturn(status);

        Assertions.assertDoesNotThrow(() -> {
            statusServiceService.saveNewStatus(status);
        });
    }

    @Test
    public void shouldNotCreateNewStatusWithNullScore(){
        Status status = new Status(1, 0, "Spain",
                LocalDate.parse("2020-01-01"));
        when(mockStatusRepository.save(status)).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(status));
        Assertions.assertEquals("Can't create a status with a null or negative score", ex.getMessage(), "Null score prevented status creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithNegativeScore(){
        Status status = new Status(1, -25, "Spain",
                LocalDate.parse("2020-01-01"));
        when(mockStatusRepository.save(status)).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(status));
        Assertions.assertEquals("Can't create a status with a null or negative score", ex.getMessage(), "Negative score prevented status creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithEmptyStringLocation(){
        Status status = new Status(1, 54, "",
                LocalDate.parse("2020-01-01"));
        when(mockStatusRepository.save(status)).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(status));
        Assertions.assertEquals("Can't create a status without a location", ex.getMessage(), "No provided location prevented status creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithNullLocation(){
        Status status = new Status(1, 54, null,
                LocalDate.parse("2020-01-01"));
        when(mockStatusRepository.save(status)).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(status));
        Assertions.assertEquals("Can't create a status without a location", ex.getMessage(), "Null location prevented status creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithNullDate(){
        Status status = new Status(1, 54, "Spain",
                null);
        when(mockStatusRepository.save(status)).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(status));
        Assertions.assertEquals("Can't create a status with a null creation date", ex.getMessage(), "Null creation date prevented status creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithFutureDate(){
        Status status = new Status(1, 54, "Spain",
                LocalDate.parse("2028-01-01"));
        when(mockStatusRepository.save(status)).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(status));
        Assertions.assertEquals("Can't create a status with a date in the future", ex.getMessage(), "Status creation cannot use a creation date in the future");
    }

    @Test
    public void shouldGetStatusByLocation(){
        Status status = new Status(1, 54, "Spain",
                LocalDate.parse("2022-01-01"));
        when(mockStatusRepository.findByLocation(status.getLocation())).thenReturn(status);

        Assertions.assertEquals(status, statusServiceService.getStatusByLocation(status.getLocation()), "Status was successfully found by location");


//        Assertions.assertDoesNotThrow(() -> {
//            statusServiceService.getStatusByLocation(status.getLocation());
//        });
    }

    @Test
    public void shouldNotGetStatusByInvalidLocation(){
        Status status = new Status(1, 54, "Spain",
                LocalDate.parse("2022-01-01"));
        when(mockStatusRepository.findByLocation(status.getLocation())).thenReturn(status);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.getStatusByLocation("InvalidLocation"));
        Assertions.assertEquals("InvalidLocation could not be found", ex.getMessage(), "Status cannot be found by invalid location");

    }
}