package com.appointment.to.doctor.controllers;

import com.appointment.to.doctor.models.Doctor;
import com.appointment.to.doctor.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @GetMapping
    private List<Doctor> get() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    private Optional<Doctor> get(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private Doctor add(@RequestBody Doctor doctor) {
        return repository.save(doctor);
    }

    @PutMapping("{id}")
    private Doctor update(@RequestBody Doctor doctor, @PathVariable Long id) {
        return repository.findById(id)
                .map(mod -> {
                    mod.setFullName(doctor.getFullName());
                    mod.setBirthdate(doctor.getBirthdate());
                    mod.setGuardian(doctor.getGuardian());
                    mod.setSpecialization(doctor.getSpecialization());
                    mod.setCabinetNumber(doctor.getCabinetNumber());
                   return repository.save(mod);
                }).orElseGet(() -> repository.save(doctor));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
