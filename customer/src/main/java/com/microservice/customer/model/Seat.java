package com.microservice.customer.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    private Long seatId;
    private String seatNo;
    private Boolean isBooked = false;
}
