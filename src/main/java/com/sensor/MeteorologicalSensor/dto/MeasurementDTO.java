package com.sensor.MeteorologicalSensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDTO {
    private SensorDTO sensor;
    @Min(value = -100, message = "Value must be not less -100")
    @Max(value = 100, message = "Value must be not greater 100")
    private double value;
    private boolean raining;
}
