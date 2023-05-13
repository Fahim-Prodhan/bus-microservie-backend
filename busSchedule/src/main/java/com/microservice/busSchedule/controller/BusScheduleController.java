package com.microservice.busSchedule.controller;

import com.microservice.busSchedule.model.BusSchedule;
import com.microservice.busSchedule.model.Seat;
import com.microservice.busSchedule.service.BusScheduleService;
import com.microservice.busSchedule.service.SeatService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("api/schedule")
@CrossOrigin("*")
public class BusScheduleController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private BusScheduleService busScheduleService;

    private Logger logger = LoggerFactory.getLogger(BusScheduleController.class);

    @PostMapping
    public ResponseEntity<BusSchedule> addSchedule(@RequestBody BusSchedule busSchedule) {

        BusSchedule busSchedule1 = this.busScheduleService.addBusSchedule(busSchedule);
        for (int i = 1; i <= 40; i++) {
            Seat seat = new Seat();
            seat.setSeatNo("Seat " + i);
            seat.setBooked(false);
            seat.setScheduleId(busSchedule.getScheduleId());
            seatService.addSeat(seat);
        }

        return ResponseEntity.ok(busSchedule1);
    }

    @CircuitBreaker(name = "routeBreaker",fallbackMethod = "routeFallback")
    @Retry(name = "routeBreaker", fallbackMethod = "routeFallback")
//    @RateLimiter(name = "routeBreaker", fallbackMethod = "routeFallback")
    @GetMapping("/{bId}")
    public ResponseEntity<BusSchedule> getSingleSchedule(@PathVariable("bId") String bId) {
        BusSchedule busSchedule = this.busScheduleService.getBusSchedule(bId);
        return ResponseEntity.ok(busSchedule);
    }
    //fallback method for delete operation

    @PutMapping
    public ResponseEntity<BusSchedule> updateSchedule(@RequestBody BusSchedule busSchedule) {
        BusSchedule busSchedule1 = this.busScheduleService.updateBusSchedule(busSchedule);
        return ResponseEntity.ok(busSchedule1);
    }


    @CircuitBreaker(name = "routeBreaker",fallbackMethod = "routeFallback")
    @Retry(name = "routeBreaker", fallbackMethod = "routeFallback")
//    @RateLimiter(name = "routeBreaker", fallbackMethod = "routeFallback")
    @GetMapping
    public ResponseEntity<?> getAllSchedule() {
        Set<BusSchedule> allSchedules = this.busScheduleService.getAllSchedules();
        return ResponseEntity.ok(allSchedules);
    }

    //fallback method for delete operation
    public ResponseEntity<?> routeFallback(Exception exception) {
        logger.info("Being service down Fallback is executed!!!",exception.getMessage());

        BusSchedule busSchedule = BusSchedule.builder()
                .busId("Dummy-bus-Id-22244322")
                .scheduleId("Dummy-ScheduleId-346789")
                .date(new Date())
                .startAndEnd("Dummy Info")
                .build();
        return new ResponseEntity<>(busSchedule, HttpStatus.OK);
    }


    @CircuitBreaker(name = "routeBreaker",fallbackMethod = "routeFallback")
    @Retry(name = "routeBreaker", fallbackMethod = "routeFallback")
    @RateLimiter(name = "routeBreaker", fallbackMethod = "routeFallback")
    @DeleteMapping("/{bId}")
    public void delete(@PathVariable("bId") String bId) {
        this.busScheduleService.deleteBusSchedule(bId);
    }


    //fallback method for delete operation
    public String routeFallback(String bId,Exception exception) {
        logger.info("Being service down Fallback is executed!!!",exception.getMessage());
        return "Dummy Delete";

    }


    @GetMapping("/count")
    public long getCountOfSchedule() {
        return this.busScheduleService.countSchedule();
    }

}
