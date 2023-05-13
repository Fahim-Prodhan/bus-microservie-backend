package com.microservice.busSchedule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BusSchedule {
    @Id
    private String scheduleId;
    private String  busId;
    private String startAndEnd;
    private Date date;

    @Transient
    private Bus bus;

    @Transient
    private List<Routes> routes = new ArrayList<>();

    @Transient
    private List<Seat> seats = new ArrayList<>();




//    private Set<Seat> seats = new HashSet<>();

//    private Set<Routes> routes;

//    private String time;
//    private String price;
//    private String pickup;
//    private String destination;




//    @OneToOne(mappedBy = "schedule",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnore
//    private Booking booking;


}


