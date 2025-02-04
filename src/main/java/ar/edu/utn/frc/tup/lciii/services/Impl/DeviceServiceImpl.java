package ar.edu.utn.frc.tup.lciii.services.Impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseDeviceDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.services.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements IDeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    @Override
    public ResponseDeviceDto createDevice(PostDeviceDto postDevice) {
        Optional<Device> exist = deviceRepository.findByHostName(postDevice.getHostname());

        if (exist.isPresent()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"ya existe un dispositivo con ese Hostname");
        }

        Device nuevo = this.mapPostToModel(postDevice);

        Device saved;
        try{  saved = deviceRepository.save(nuevo);} catch (Exception e) {
            throw new RuntimeException(e);
        }


        ResponseDeviceDto response = this.mapModelToResponse(saved);

        return response;
    }

    @Override
    public List<ResponseDeviceDto> getDeviceByType(DeviceType type) {
        List<ResponseDeviceDto> lResponse = new ArrayList<>();
        List<Device> devices = deviceRepository.getDevicesByType(type);
        for(Device d : devices){
            ResponseDeviceDto response = this.mapModelToResponse(d);
        }
        return lResponse;
    }

    private Device mapPostToModel(PostDeviceDto post){
        Device device = new Device();
        device.setHostName(post.getHostname());
        device.setType(post.getType());
        device.setCreatedDate(LocalDateTime.now());
        device.setOs(post.getOs());
        device.setMacAddress(post.getMacAddress());
        return device;
    }
    private ResponseDeviceDto mapModelToResponse(Device device){
        ResponseDeviceDto response = new ResponseDeviceDto();
        response.setId(device.getId());
        response.setHostname(device.getHostName());
        response.setType(device.getType());
        response.setOs(device.getOs());
        response.setMacAddress(device.getMacAddress());
        return response;
    }
}
