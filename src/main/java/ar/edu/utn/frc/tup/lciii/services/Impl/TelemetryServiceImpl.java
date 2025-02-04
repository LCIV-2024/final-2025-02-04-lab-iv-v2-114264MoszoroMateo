package ar.edu.utn.frc.tup.lciii.services.Impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseTelemetryDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repositories.TelemetryRepository;
import ar.edu.utn.frc.tup.lciii.services.ITelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelemetryServiceImpl implements ITelemetryService {

    private final TelemetryRepository telemetryRepository;

    private final DeviceRepository deviceRepository;

    public TelemetryServiceImpl(TelemetryRepository telemetryRepository, DeviceRepository deviceRepository) {
        this.telemetryRepository = telemetryRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public ResponseTelemetryDto createTelemetry(PostTelemetryDto postTelemetry) {
        Optional<Device> exist = deviceRepository.findByHostName(postTelemetry.getHostname());
        if (exist.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"No existe Device con el Hostname: "+ postTelemetry.getHostname());
        }
        Telemetry nuevo = new Telemetry();
        nuevo.setIp(postTelemetry.getIp());
        //nuevo.setDevice(exist.get());
        nuevo.setHostname(postTelemetry.getHostname());
        nuevo.setCpuUsage(postTelemetry.getCpuUsage());
        nuevo.setHostDiskFree(postTelemetry.getHostDiskFree());
        nuevo.setMicrophoneState(postTelemetry.getMicrophoneState());
        nuevo.setScreenCaptureAllowed(postTelemetry.getScreenCaptureAllowed());
        nuevo.setAudioCaptureAllowed(postTelemetry.getAudioCaptureAllowed());
        nuevo.setDataDate(postTelemetry.getDataDate());


        telemetryRepository.save(nuevo);
        ResponseTelemetryDto response = new ResponseTelemetryDto();
        response.setIp(nuevo.getIp());
        response.setDataDate(nuevo.getDataDate());
        response.setHostname(nuevo.getDevice().getHostName());
        response.setMicrophoneState(nuevo.getMicrophoneState());
        response.setScreenCaptureAllowed(nuevo.getScreenCaptureAllowed());
        response.setAudioCaptureAllowed(nuevo.getAudioCaptureAllowed());
        return response;
    }

    @Override
    public List<ResponseTelemetryDto> getAllTelemetry() {
        List<ResponseTelemetryDto> lResponse = new ArrayList<>();
        List<Telemetry> lTelemetry = telemetryRepository.findAll();

        for(Telemetry t : lTelemetry){
            ResponseTelemetryDto response = new ResponseTelemetryDto();
            response.setIp(t.getIp());
            response.setDataDate(t.getDataDate());
            response.setHostname(t.getDevice().getHostName());
            response.setMicrophoneState(t.getMicrophoneState());
            response.setScreenCaptureAllowed(t.getScreenCaptureAllowed());
            response.setAudioCaptureAllowed(t.getAudioCaptureAllowed());
            lResponse.add(response);
        }
        return lResponse;
    }
}
