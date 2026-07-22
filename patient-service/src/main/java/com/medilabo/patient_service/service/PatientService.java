package com.medilabo.patient_service.service;

import com.medilabo.patient_service.dto.PatientDTO;
import com.medilabo.patient_service.model.Patient;
import com.medilabo.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;
import com.medilabo.patient_service.exception.PatientNotFoundException;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        return toDto(patient);
    }

    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = new Patient(
                dto.getLastName(), dto.getFirstName(), dto.getDateOfBirth(),
                dto.getGender(), dto.getAddress(), dto.getPhone()
        );
        return toDto(patientRepository.save(patient));
    }

    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        patient.setLastName(dto.getLastName());
        patient.setFirstName(dto.getFirstName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());

        return toDto(patientRepository.save(patient));
    }

    private PatientDTO toDto(Patient p) {
        return new PatientDTO(p.getId(), p.getLastName(), p.getFirstName(),
                p.getDateOfBirth(), p.getGender(), p.getAddress(), p.getPhone());
    }
}