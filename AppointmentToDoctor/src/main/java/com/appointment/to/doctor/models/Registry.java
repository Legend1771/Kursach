package com.appointment.to.doctor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registry")
public final class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registry_id", updatable = false, nullable = false)
    private Long id;

    //Пациент
    @JsonIgnoreProperties("patients")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Registry registry = (Registry) o;
        return id != null && Objects.equals(id, registry.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
