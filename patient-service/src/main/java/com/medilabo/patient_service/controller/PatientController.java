package com.medilabo.patient_service.controller;

import com.medilabo.patient_service.dto.PatientDTO;
import com.medilabo.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }
}