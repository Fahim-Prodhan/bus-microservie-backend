package com.microservice.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Routes {
    private String routeId;
    private String scheduleId;
    private String busId;
    private String departure;
    private String destination;
    private String time;
    private Date date;
    private String price;

}
