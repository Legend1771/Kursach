package com.appointment.to.doctor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "tickets")
@NoArgsConstructor
@Entity
public final class EntryTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", updatable = false, nullable = false)
    private Long id;

    //Доктор
    @JsonIgnoreProperties("doctors")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

    //Регистрация
    @JsonIgnoreProperties("registry")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "registry_id", referencedColumnName = "registry_id")
    private Registry registry;

    //Дата осмотра
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntryTicket that = (EntryTicket) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
