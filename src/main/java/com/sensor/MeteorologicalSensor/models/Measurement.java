package com.sensor.MeteorologicalSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Measurement {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "name", referencedColumnName = "name")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Sensor sensor;
    @Column
    @Min(value = -100, message = "Value must be not less -100")
    @Max(value = 100, message = "Value must be greater 100")
    private double value;
    @Column
    private boolean raining;
    @Column(name = "created_at")
    private LocalDateTime timestamp;

    public Measurement(double value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }
}
