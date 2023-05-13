package com.microservice.customer.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    private String customerId;
    private String routeId;
    private String scheduleId;
    private Long userId;
    private Long seatId;
    private String customerName;
    private String customerNumber;
    @ElementCollection
    private List<String> seatNo;
    @Transient
    private BusSchedule busSchedule;
    @Transient
    private Routes routes;
    @Transient
    private Seat seat;
}
