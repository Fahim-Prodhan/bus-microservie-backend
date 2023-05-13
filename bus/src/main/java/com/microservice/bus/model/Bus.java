package com.microservice.bus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "bus")
public class Bus {
    @Id
    private String busId;
    private String busCode;
    private String busNumber;
    private String busName;
//    private String scheduleId;
}
