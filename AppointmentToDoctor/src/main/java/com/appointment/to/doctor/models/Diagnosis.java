package com.appointment.to.doctor.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "diagnoses")
@NoArgsConstructor
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return id != null && Objects.equals(id, diagnosis.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
