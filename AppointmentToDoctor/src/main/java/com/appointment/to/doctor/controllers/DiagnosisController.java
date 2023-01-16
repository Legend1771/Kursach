package com.appointment.to.doctor.controllers;

import com.appointment.to.doctor.models.Diagnosis;
import com.appointment.to.doctor.repositories.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnoses")
public class DiagnosisController {
    @Autowired
    private DiagnosisRepository repository;

    @GetMapping
    private List<Diagnosis> get() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    private Optional<Diagnosis> get(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private Diagnosis add(@RequestBody Diagnosis model) {
        return repository.save(model);
    }

    @PutMapping("{id}")
    private Diagnosis update(@RequestBody Diagnosis model, @PathVariable Long id) {
        return repository.findById(id)
                .map(mod -> {
                    mod.setName(model.getName());
                    return repository.save(mod);
                }).orElseGet(() -> repository.save(model));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
