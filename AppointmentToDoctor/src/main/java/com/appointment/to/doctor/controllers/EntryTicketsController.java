package com.appointment.to.doctor.controllers;

import com.appointment.to.doctor.models.EntryTicket;
import com.appointment.to.doctor.repositories.EntryTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class EntryTicketsController {
    @Autowired
    private EntryTicketRepository repository;

    @GetMapping
    private List<EntryTicket> get() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    private Optional<EntryTicket> get(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    private EntryTicket add(@RequestBody EntryTicket model) {
        return repository.save(model);
    }

    @PutMapping("{id}")
    private EntryTicket update(@RequestBody EntryTicket model, @PathVariable Long id) {
        return repository.findById(id)
                .map(mod -> {
                    mod.setDoctor(model.getDoctor());
                    mod.setRegistry(model.getRegistry());
                    mod.setDate(model.getDate());
                    return repository.save(mod);
                }).orElseGet(() -> repository.save(model));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
