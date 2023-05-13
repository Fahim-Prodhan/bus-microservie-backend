package com.microservice.busSchedule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;
    private String scheduleId;
    private String seatNo;
    private Boolean booked = false;
    

}
