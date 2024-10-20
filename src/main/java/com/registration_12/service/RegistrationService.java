package com.registration_12.service;

import com.registration_12.entity.Registration;
import com.registration_12.exception.ResourceNotFoundException;
import com.registration_12.payload.RegistrationDto;
import com.registration_12.repository.RegistrationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations() {
     List<Registration>registrations =  registrationRepository.findAll();
     List<RegistrationDto>dtos = registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }
    public RegistrationDto createRegistration(RegistrationDto registrationDto){
        Registration registration = mapToEntity(registrationDto);
       Registration saveEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(saveEntity);
        return dto;
    }
    Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto,Registration.class);
        return registration;
    }
    RegistrationDto mapToDto(Registration registration){
       RegistrationDto dto = modelMapper.map(registration,RegistrationDto.class);
       return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
       Registration r =  registrationRepository.findById(id).get();
       r.setName(registration.getName());
       r.setEmail(registration.getEmail());
       r.setMobile(registration.getMobile());
    Registration saveEnity =    registrationRepository.save(r);
    return saveEnity;
    }

    public RegistrationDto getRegistrationsById(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Record not found"));
        return mapToDto(registration);
    }
}