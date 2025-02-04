package ar.edu.utn.frc.tup.lciii.services.Impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repositories.TelemetryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TelemetryServiceImplTest {
    @SpyBean
    private TelemetryServiceImpl telemetryService;
    @SpyBean
    private DeviceRepository deviceRepository;
    @SpyBean
    private TelemetryRepository telemetryRepository;

    @Test
    @Disabled
    public void deviceNotFound(){
        PostTelemetryDto post = new PostTelemetryDto();

        when(deviceRepository.findByHostName("awdawd")).thenReturn(Optional.empty());

        assertEquals(HttpClientErrorException.class,telemetryService.createTelemetry(post));
    }

}