package com.sensor.MeteorologicalSensor.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SensorErrorResponse {
    private String message;
    private long timestamp;
}
