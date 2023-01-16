package com.appointment.to.doctor.controllers;

import com.appointment.to.doctor.models.Treatment;
import com.appointment.to.doctor.repositories.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/treatment")
public class TreatmentController {
    @Autowired
    private TreatmentRepository repository;

    @GetMapping
    private List<Treatment> get() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    private Optional<Treatment> get(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private Treatment add(@RequestBody Treatment model) {
        return repository.save(model);
    }

    @PutMapping("{id}")
    private Treatment update(@RequestBody Treatment model, @PathVariable Long id) {
        return repository.findById(id)
                .map(mod -> {
                    mod.setDoctor(model.getDoctor());
                    mod.setPatient(model.getPatient());
                    mod.setDiagnosis(model.getDiagnosis());
                    mod.setDurationTreatment(model.getDurationTreatment());
                    mod.setStageDisease(model.getStageDisease());
                    return repository.save(mod);
                }).orElseGet(() -> repository.save(model));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
