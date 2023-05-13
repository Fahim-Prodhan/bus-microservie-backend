package com.microservice.busSchedule.service;



import com.microservice.busSchedule.model.BusSchedule;

import java.util.Set;

public interface BusScheduleService {
    public BusSchedule addBusSchedule(BusSchedule busSchedule);

    public BusSchedule updateBusSchedule(BusSchedule busSchedule);

    public void deleteBusSchedule(String sId);

//    public BusSchedule getSingleSchedule(String sId);

    public Set<BusSchedule> getAllSchedules();

//    public List<Customer> getCustomersByScheduleId(Long scheduleId);

    public long countSchedule();

    BusSchedule getBusSchedule(String bId);

}
