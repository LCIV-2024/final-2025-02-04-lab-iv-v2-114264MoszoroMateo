package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseDeviceDto;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDeviceService {
    ResponseDeviceDto createDevice(PostDeviceDto postDevice);
    List<ResponseDeviceDto> getDeviceByType(DeviceType type);
}
