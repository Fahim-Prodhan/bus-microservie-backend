package com.microservice.busSchedule.controller;

import com.microservice.busSchedule.model.BusSchedule;
import com.microservice.busSchedule.model.Seat;
import com.microservice.busSchedule.service.BusScheduleService;
import com.microservice.busSchedule.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("api/seat")
public class SeatController {

    @Autowired
    private BusScheduleService busScheduleService;

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<Seat> addSeat (@RequestBody Seat seat) {
        return ResponseEntity.ok(this.seatService.addSeat(seat));
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeat(@PathVariable("seatId") Long seatId){
        return ResponseEntity.ok( this.seatService.getSeat(seatId));
    }

    @DeleteMapping("/{seatId}")
    public void deleteSeat(@PathVariable("seatId") Long seatId) {
        this.seatService.deleteSeat(seatId);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Seat>> getAllSeats() {
        return ResponseEntity.ok(this.seatService.getAllSeats());
    }

    @PutMapping
    public ResponseEntity<Seat> updateSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(this.seatService.updateSeat(seat));
    }



    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<?> getSeatsOfSchedule(@PathVariable("scheduleId") String  scheduleId) {
        BusSchedule busSchedule = this.busScheduleService.getBusSchedule(scheduleId);
        List<Seat> seatSet =busSchedule.getSeats();
        List list = new ArrayList<>(seatSet);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/active")
    public ResponseEntity<Set<Seat>> getActiveSeats() {
        return ResponseEntity.ok(this.seatService.getAllActiveSeats());
    }

    @GetMapping("/count")
    public long getCountOfSeats() {
        return this.seatService.getCountOfSeat();
    }


    @GetMapping
    public ResponseEntity<List<Seat>> getSeatsByIds(@RequestParam List<Long> ids) {
        List<Seat> seats = seatService.getSeatsByIds(ids);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/schedule/{sId}/seats")
    public ResponseEntity<List<Seat>> getAllSeatsOfSchedules(@PathVariable String sId) {
        List<Seat> allSeatsOfSchedules = seatService.getAllSeatsOfSchedules(sId);
        return ResponseEntity.ok(allSeatsOfSchedules);
    }

    @DeleteMapping("/schedule/{sId}")
    public void deleteAllSeatsOfSchedules(@PathVariable String sId) {
        seatService.deleteSeatByScheduleId(sId);
    }
}
