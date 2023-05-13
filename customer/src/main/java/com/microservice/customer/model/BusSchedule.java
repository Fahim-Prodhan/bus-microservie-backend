package com.microservice.customer.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusSchedule {
    private String scheduleId;
    private String  busId;
    private String startAndEnd;
    private Date date;
}
