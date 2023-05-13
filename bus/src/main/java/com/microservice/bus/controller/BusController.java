package com.microservice.bus.controller;

import com.microservice.bus.model.Bus;
import com.microservice.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(this.busService.addBus(bus));
    }

    @GetMapping("/{busId}")
    public ResponseEntity<Bus> getBus(@PathVariable("busId") String busId) {
        return ResponseEntity.ok(this.busService.getBus(busId));
    }

    @PutMapping
    public ResponseEntity<Bus> updateBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(this.busService.updateBus(bus));
    }

    @DeleteMapping("/{busId}")
    public void deleteBus(@PathVariable("busId") String busId) {
        this.busService.deleteBus(busId);
    }

    @GetMapping
    public ResponseEntity<?> getAllBusses() {
        return ResponseEntity.ok(this.busService.getAllBusses());
    }

    @GetMapping("/count")
    public long getBusCount() {
        return this.busService.countBus();
    }


}
