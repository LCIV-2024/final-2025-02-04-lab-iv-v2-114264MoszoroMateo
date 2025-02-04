package ar.edu.utn.frc.tup.lciii.dtos.common;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTelemetryDto {

    private String ip;
    private LocalDateTime dataDate;

    private Double hostDiskFree;

    private String microphoneState;

    private Boolean screenCaptureAllowed;

    private Boolean audioCaptureAllowed;
    private String hostname;
}
