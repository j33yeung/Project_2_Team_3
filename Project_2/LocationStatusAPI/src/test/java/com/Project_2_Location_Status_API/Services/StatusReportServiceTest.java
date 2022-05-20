package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.Entities.StatusReport;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatusReportServiceTest {
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
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        Assertions.assertDoesNotThrow(() -> {
            statusServiceService.saveNewStatus(statusReport);
        });
    }

    @Test
    public void shouldNotCreateNewStatusWithNullScore(){
        StatusReport statusReport = new StatusReport(1, 0, "Spain",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a null or negative score", ex.getMessage(), "Null score prevented statusReport creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithNegativeScore(){
        StatusReport statusReport = new StatusReport(1, -25, "Spain",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a null or negative score", ex.getMessage(), "Negative score prevented statusReport creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithEmptyStringLocation(){
        StatusReport statusReport = new StatusReport(1, 54, "",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport without a location", ex.getMessage(), "No provided location prevented statusReport creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithNullLocation(){
        StatusReport statusReport = new StatusReport(1, 54, null,
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport without a location", ex.getMessage(), "Null location prevented statusReport creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithNullDate(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                null,"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a null creation date", ex.getMessage(), "Null creation date prevented statusReport creation");
    }

    @Test
    public void shouldNotCreateNewStatusWithFutureDate(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2028-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a date in the future", ex.getMessage(), "StatusReport creation cannot use a creation date in the future");
    }

    @Test
    public void shouldGetStatusByLocation(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2022-01-01"),"Safe to Travel");
        when(mockStatusRepository.findByLocation(statusReport.getLocation())).thenReturn(statusReport);

        Assertions.assertEquals(statusReport, statusServiceService.getStatusByLocation(statusReport.getLocation()), "StatusReport was successfully found by location");


//        Assertions.assertDoesNotThrow(() -> {
//            statusServiceService.getStatusByLocation(statusReport.getLocation());
//        });
    }

    @Test
    public void shouldNotGetStatusByInvalidLocation(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2022-01-01"),"Safe to Travel");
        when(mockStatusRepository.findByLocation(statusReport.getLocation())).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusServiceService.getStatusByLocation("InvalidLocation"));
        Assertions.assertEquals("InvalidLocation could not be found", ex.getMessage(), "StatusReport cannot be found by invalid location");

    }
}