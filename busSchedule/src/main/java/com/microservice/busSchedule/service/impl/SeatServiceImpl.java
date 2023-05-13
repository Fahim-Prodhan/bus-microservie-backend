package com.microservice.busSchedule.service.impl;


import com.microservice.busSchedule.model.BusSchedule;
import com.microservice.busSchedule.model.Seat;
import com.microservice.busSchedule.repository.BusScheduleRepo;
import com.microservice.busSchedule.repository.SeatRepo;
import com.microservice.busSchedule.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private BusScheduleRepo busScheduleRepo;

    @Override
    public Seat addSeat(Seat seat) {
        return this.seatRepo.save(seat);
    }

    @Override
    public Seat updateSeat(Seat seat) {
        return this.seatRepo.save(seat);
    }

    @Override
    public Seat getSeat(Long seatId) {
        return this.seatRepo.findById(seatId).get();
    }

    @Override
    public void deleteSeat(Long seatId) {
        this.seatRepo.deleteById(seatId);
    }

    @Transactional
    @Override
    public void deleteSeatByScheduleId(String sId) {
        seatRepo.deleteByScheduleId(sId);
    }


    @Override
    public Set<Seat> getAllSeats() {
        return new HashSet<>(this.seatRepo.findAll());
    }

    @Override
    public Set<Seat> getAllActiveSeats() {
        return this.seatRepo.findAllByBooked(true);
    }

    @Override
    public long getCountOfSeat() {
        return this.seatRepo.count();
    }



    @Override
    public List<Seat> getSeatsOfSchedule(String sId) {
        this.seatRepo.getAllByScheduleId(sId);
        return null;
    }

    @Override
    public List<Seat> getSeatsByIds(List<Long> ids) {
        List<Seat> bySeatIdIn = seatRepo.findBySeatIdIn(ids);
        return bySeatIdIn;
    }

    @Override
    public List<Seat> getAllSeatsOfSchedules(String sId) {
        List<Seat> byScheduleIdIn = seatRepo.findByScheduleId(sId);
        return byScheduleIdIn;
    }




//    @Override
//    public Set<Seat> getSeatsOfSchedule(BusSchedule schedule) {
//        return this.seatRepo.findByBusSchedule(schedule);
//    }
}
