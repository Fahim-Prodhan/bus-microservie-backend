package com.microservice.routes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Routes {
    @Id
    private String routeId;
    private String scheduleId;
    private String busId;
    private String departure;
    private String destination;
    private String time;
    private Date date;
    private Double price;
    @Transient
    private Bus bus;
}
