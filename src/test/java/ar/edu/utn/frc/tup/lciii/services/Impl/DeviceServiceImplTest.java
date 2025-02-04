package ar.edu.utn.frc.tup.lciii.services.Impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseDeviceDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.any;

@SpringBootTest
class DeviceServiceImplTest {
    @SpyBean
    private DeviceServiceImpl deviceService;
    @SpyBean
    private DeviceRepository deviceRepository;
    @Captor
    private ArgumentCaptor<Device> capturing;


    @Test
    public void PostDevice(){
        PostDeviceDto post = new PostDeviceDto("pc-001", DeviceType.Tablet,"Win10","awdas123");

        when(deviceRepository.findByHostName(post.getHostname())).thenReturn(Optional.empty());


        ResponseDeviceDto response = deviceService.createDevice(post);

        verify(deviceRepository).save(capturing.capture());
        Device saved = capturing.getValue();
        assertEquals(response.getHostname(),saved.getHostName());
        assertEquals(response.getOs(),saved.getOs());
        assertEquals(response.getMacAddress(),saved.getMacAddress());

    }

    @Test
    public void mapperPostToModel(){
        PostDeviceDto post = new PostDeviceDto("pc-001", DeviceType.Tablet,"Win10","awdas123");

        Device mapped = ReflectionTestUtils.invokeMethod(deviceService,"mapPostToModel",post);

        assertEquals(mapped.getHostName(),post.getHostname());
        assertEquals(mapped.getOs(),post.getOs());
    }

    @Test
    public void mapperModelToResponse(){
        Device device = new Device(null,"pc-001",null ,DeviceType.Tablet, LocalDateTime.now(),"win10","123123123");

        ResponseDeviceDto mapped = ReflectionTestUtils.invokeMethod(deviceService,"mapModelToResponse",device);

        assertEquals(mapped.getHostname(),device.getHostName());
        assertEquals(mapped.getOs(),device.getOs());
    }

    

}