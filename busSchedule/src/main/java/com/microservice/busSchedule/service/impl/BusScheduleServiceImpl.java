package com.microservice.busSchedule.service.impl;

import com.microservice.busSchedule.model.Bus;
import com.microservice.busSchedule.model.BusSchedule;
import com.microservice.busSchedule.model.Routes;
import com.microservice.busSchedule.model.Seat;
import com.microservice.busSchedule.repository.BusScheduleRepo;
import com.microservice.busSchedule.service.BusScheduleService;
import com.microservice.busSchedule.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {

    @Autowired
    private BusScheduleRepo busScheduleRepo;

    @Autowired
    private SeatService seatService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(BusScheduleServiceImpl.class);


    @Override
    public BusSchedule addBusSchedule(BusSchedule busSchedule) {
        busSchedule.setScheduleId(UUID.randomUUID().toString());
        return this.busScheduleRepo.save(busSchedule);
    }



    @Override
    public BusSchedule updateBusSchedule(BusSchedule busSchedule) {
        return this.busScheduleRepo.save(busSchedule);
    }

    @Override
    public void deleteBusSchedule(String sId) {
        restTemplate.delete("http://ROUTES/api/route/schedule/"+sId);
        seatService.deleteSeatByScheduleId(sId);
        busScheduleRepo.deleteById(sId);
    }

//    @Override
//    public BusSchedule getSingleSchedule(String sId) {
//        return busScheduleRepo.findById(sId).get();
//
//    }


    @Transactional
    @Override
    public Set<BusSchedule> getAllSchedules() {
        HashSet<BusSchedule> busSchedules = new HashSet<>(this.busScheduleRepo.findAll());

        for (BusSchedule busSchedule : busSchedules){
            Routes[] routes = restTemplate.getForObject("http://ROUTES/api/route/schedule/" + busSchedule.getScheduleId(), Routes[].class);
            logger.info("{} ",routes);
            busSchedule.setRoutes(List.of(routes));

            List<Seat> allSeatsOfSchedules = seatService.getAllSeatsOfSchedules(busSchedule.getScheduleId());
            busSchedule.setSeats(allSeatsOfSchedules);

            ResponseEntity<Bus> busResponseEntity = restTemplate.getForEntity("http://Bus/api/bus/" + busSchedule.getBusId(), Bus.class);
            Bus body = busResponseEntity.getBody();
            busSchedule.setBus(body);


        }
            return busSchedules;
    }



    @Override
    public long countSchedule() {
        return this.busScheduleRepo.count();
    }

    @Transactional
    @Override
    public BusSchedule getBusSchedule(String  bId) {
        //getting single schedule from current database
        BusSchedule busSchedule = busScheduleRepo.findById(bId).get();

        //get routes from another database
        //localhost:3032/api/route/schedule/6f258071-6927-4aa6-bd67-abc55f50e56e
        Routes[] routes = restTemplate.getForObject("http://ROUTES/api/route/schedule/" + busSchedule.getScheduleId(), Routes[].class);
        logger.info("{} ",routes);
        busSchedule.setRoutes(List.of(routes));

        List<Seat> allSeatsOfSchedules = seatService.getAllSeatsOfSchedules(bId);
        busSchedule.setSeats(allSeatsOfSchedules);

        ResponseEntity<Bus> bus = restTemplate.getForEntity("http://BUS/api/bus/" + busSchedule.getBusId(), Bus.class);
        Bus busBody = bus.getBody();
        busSchedule.setBus(busBody);

        return busSchedule;
    }


}
