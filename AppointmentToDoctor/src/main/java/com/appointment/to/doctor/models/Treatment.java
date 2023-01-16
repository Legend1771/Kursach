package com.appointment.to.doctor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

//Лечение
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment")
public final class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id", updatable = false, nullable = false)
    private Long id;

    //Диагноз
    @JsonIgnoreProperties("diagnoses")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "diagnosis_id")
    private Diagnosis diagnosis;

    //Стадия заболевания
    private String stageDisease;

    //Доктор
    @JsonIgnoreProperties("doctors")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

    //Пациент
    @JsonIgnoreProperties("patients")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;

    //Срок лечения
    private String durationTreatment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Treatment treatment = (Treatment) o;
        return id != null && Objects.equals(id, treatment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
