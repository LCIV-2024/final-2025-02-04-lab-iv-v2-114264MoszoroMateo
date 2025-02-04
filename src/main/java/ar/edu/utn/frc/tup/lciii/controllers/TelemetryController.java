package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseTelemetryDto;
import ar.edu.utn.frc.tup.lciii.services.Impl.TelemetryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/telemetry")
public class TelemetryController {
    private final TelemetryServiceImpl telemetryService;

    @GetMapping()
    public ResponseEntity<List<ResponseTelemetryDto>> getTelemetries(){
        List<ResponseTelemetryDto> response = telemetryService.getAllTelemetry();
        return ResponseEntity.ok(response);
    }

    @PostMapping()
public ResponseEntity<ResponseTelemetryDto> postTelemetry(@RequestParam()PostTelemetryDto dto){
        ResponseTelemetryDto response = telemetryService.createTelemetry(dto);
        return ResponseEntity.ok(response);
    }

}