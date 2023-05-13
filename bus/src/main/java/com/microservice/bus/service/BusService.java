package com.microservice.bus.service;


import com.microservice.bus.model.Bus;

import java.util.Set;

public interface BusService {
    public Bus addBus(Bus bus);
    public Bus updateBus(Bus bus);
    public Bus getBus(String busId);
    public void deleteBus(String busId);
    public Set<Bus> getAllBusses();
    public long countBus();
}
