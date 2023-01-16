package com.appointment.to.doctor.controllers;

import com.appointment.to.doctor.models.Patient;
import com.appointment.to.doctor.repositories.PatientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @GetMapping
    private List<Patient> get() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    private Optional<Patient> get(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private Patient add(@RequestBody Patient patient) {
        return repository.save(patient);
    }

    @PutMapping("{id}")
    private Patient update(@RequestBody Patient patient, @PathVariable Long id) {
        return repository.findById(id)
                .map(model -> {
                    model.setFullName(patient.getFullName());
                    model.setBirthdate(patient.getBirthdate());
                    model.setWorkplace(patient.getWorkplace());
                    model.setStudyPlace(patient.getStudyPlace());
                    model.setPolicyNumber(patient.getPolicyNumber());
                    return repository.save(model);
                }).orElseGet(() -> repository.save(patient));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
