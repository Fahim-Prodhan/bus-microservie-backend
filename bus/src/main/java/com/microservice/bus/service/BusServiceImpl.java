package com.microservice.bus.service;

import com.microservice.bus.model.Bus;
import com.microservice.bus.repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepo busRepo;


    @Override
    public Bus addBus(Bus bus) {
        String randomId = UUID.randomUUID().toString();
        bus.setBusId(randomId);
        return this.busRepo.save(bus);
    }

    @Override
    public Bus updateBus(Bus bus) {
        return this.busRepo.save(bus);
    }

    @Override
    public Bus getBus(String busId) {

        return this.busRepo.findById(busId).get();
    }

    @Override
    public void deleteBus(String busId){

        this.busRepo.deleteById(busId);
    }

    @Override
    public Set<Bus> getAllBusses() {
        return new HashSet<>(this.busRepo.findAll());
    }

    @Override
    public long countBus() {
        return this.busRepo.count();
    }

}
