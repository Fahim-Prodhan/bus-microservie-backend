package com.microservice.busSchedule.service;


import com.microservice.busSchedule.model.BusSchedule;
import com.microservice.busSchedule.model.Seat;

import java.util.List;
import java.util.Set;

public interface SeatService {

    public Seat addSeat(Seat seat);
    public Seat updateSeat(Seat seat);
    public Seat getSeat(Long seatId);
    public void deleteSeat(Long seatId);

    public void deleteSeatByScheduleId(String sId);

    public Set<Seat> getAllSeats();

    public Set<Seat> getAllActiveSeats();

    public long getCountOfSeat();

    List<Seat> getSeatsOfSchedule(String sId);

    List<Seat> getSeatsByIds(List<Long> ids);

    List<Seat> getAllSeatsOfSchedules(String sId);

//    public Set<Seat> getSeatsOfSchedule(BusSchedule schedule);



}
