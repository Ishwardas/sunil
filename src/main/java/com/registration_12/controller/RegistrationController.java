package com.registration_12.controller;

import com.registration_12.entity.Registration;
import com.registration_12.payload.RegistrationDto;
import com.registration_12.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
       List<RegistrationDto> dtos = registrationService.getRegistrations();
       return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RegistrationDto>createRegistration(
            @RequestBody RegistrationDto registrationDto
    ){
        RegistrationDto dto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteRegistration(@RequestParam long id){
       registrationService.deleteRegistration(id);
       return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity <Registration> updateRegistration(@PathVariable long id,
                                                            @RequestBody Registration registration){
       Registration saveEntity =  registrationService.updateRegistration(id,registration);
       return new ResponseEntity<>(saveEntity,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(  @PathVariable long id){
     RegistrationDto dto = registrationService.getRegistrationsById(id);
     return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
