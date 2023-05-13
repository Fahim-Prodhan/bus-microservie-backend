package com.microservice.busSchedule.model;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bus {
    private String busId;
    private String busCode;
    private String busNumber;
    private String busName;

}
