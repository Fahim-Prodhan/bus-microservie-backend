package com.microservice.routes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
