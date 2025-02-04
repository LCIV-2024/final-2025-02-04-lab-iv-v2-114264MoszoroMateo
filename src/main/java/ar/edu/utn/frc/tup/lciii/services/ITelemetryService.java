package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseTelemetryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITelemetryService {
    ResponseTelemetryDto createTelemetry(PostTelemetryDto postTelemetry);
    List<ResponseTelemetryDto> getAllTelemetry();
}
