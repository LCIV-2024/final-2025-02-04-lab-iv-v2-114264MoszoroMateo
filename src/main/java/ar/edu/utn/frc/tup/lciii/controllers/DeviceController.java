package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseDeviceDto;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.services.IDeviceService;
import ar.edu.utn.frc.tup.lciii.services.Impl.DeviceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceServiceImpl deviceService;


    @PostMapping()
    public ResponseEntity<ResponseDeviceDto> postDevice(@RequestBody PostDeviceDto post){
        ResponseDeviceDto response = deviceService.createDevice(post);

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<ResponseDeviceDto>> getDevicesByType(@RequestParam DeviceType type){
        List<ResponseDeviceDto> response = deviceService.getDeviceByType(type);

        return ResponseEntity.ok(response);
    }


}