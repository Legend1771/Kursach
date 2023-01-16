package com.appointment.to.doctor.controllers;

import com.appointment.to.doctor.models.Registry;
import com.appointment.to.doctor.repositories.RegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registry")
public class RegistryController {
    @Autowired
    private RegistryRepository repository;

    @GetMapping
    private List<Registry> get() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    private Optional<Registry> get(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private Registry add(@RequestBody Registry model) {
        return repository.save(model);
    }

    @PutMapping("{id}")
    private Registry update(@RequestBody Registry model, @PathVariable Long id) {
        return repository.findById(id)
                .map(mod -> {
                    mod.setPatient(model.getPatient());
                    return repository.save(mod);
                }).orElseGet(() -> repository.save(model));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
