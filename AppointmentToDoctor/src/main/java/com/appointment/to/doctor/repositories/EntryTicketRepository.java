package com.appointment.to.doctor.repositories;

import com.appointment.to.doctor.models.EntryTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTicketRepository extends JpaRepository<EntryTicket, Long> {
}
