package com.microservice.customer.service.impl;


import com.microservice.customer.model.BusSchedule;
import com.microservice.customer.model.Customer;
import com.microservice.customer.model.Routes;
import com.microservice.customer.model.Seat;
import com.microservice.customer.repository.CustomerRepo;
import com.microservice.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

//    private String seatServiceUrl = "http://localhost:3031";


    @Override
    public Customer addCustomer(Customer customer) {

//        String randomId = UUID.randomUUID().toString();
//        customer.setCustomerId(randomId);

        return this.customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
//        customer.setCustomerId(UUID.randomUUID().toString());
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long  customerId) {
        Customer customer = this.customerRepo.findById(customerId).get();

        ResponseEntity<BusSchedule> schedule = restTemplate.getForEntity("http://BUS-SCHEDULE/api/schedule/" + customer.getScheduleId(), BusSchedule.class);
        BusSchedule scheduleBody = schedule.getBody();
        customer.setBusSchedule(scheduleBody);

        ResponseEntity<Routes> routes = restTemplate.getForEntity("http://ROUTES/api/route/" + customer.getRouteId(), Routes.class);
        Routes routesBody = routes.getBody();
        customer.setRoutes(routesBody);

//        ResponseEntity<Seat> seat = restTemplate.getForEntity("http://BUS-SCHEDULE/api/seat/" + customer.getSeatId(), Seat.class);
//        Seat seatBody = seat.getBody();
//        customer.setSeat(seatBody);


        return customer;

    }

    @Override
    public void deleteCustomer(Long  customerId) {
        this.customerRepo.deleteById(customerId);
    }

    @Override
    public Set<Customer> getAllCustomers() {
        HashSet<Customer> customers = new HashSet<>(this.customerRepo.findAll());

        for (Customer customer : customers) {
            ResponseEntity<BusSchedule> schedule = restTemplate.getForEntity("http://BUS-SCHEDULE/api/schedule/" + customer.getScheduleId(), BusSchedule.class);
            BusSchedule scheduleBody = schedule.getBody();
            customer.setBusSchedule(scheduleBody);

            ResponseEntity<Routes> routes = restTemplate.getForEntity("http://ROUTES/api/route/" + customer.getRouteId(), Routes.class);
            Routes routesBody = routes.getBody();
            customer.setRoutes(routesBody);

//            ResponseEntity<Seat> seat = restTemplate.getForEntity("http://BUS-SCHEDULE/api/seat/" + customer.getSeatId(), Seat.class);
//            Seat seatBody = seat.getBody();
//            customer.setSeat(seatBody);

        }
        return customers;

    }

    @Override
    public long getTotalCustomers() {
        return this.customerRepo.count();
    }

    @Transactional
    @Override
    public List<Customer> getCustomerOfSchedule(String scheduleId) {
        return this.customerRepo.findByScheduleId(scheduleId);
    }

    @Override
    public List<Customer> getCustomerOfSeat(Long seatId) {
        List<Customer> bySeatId = this.customerRepo.findBySeatId(seatId);
        return bySeatId;
    }

    @Override
    public List<Customer> getAllCustomerOfCurrentUser(Long userId) {
        List<Customer> byUserId = this.customerRepo.findByUserId(userId);
        for (Customer customer : byUserId) {
            ResponseEntity<BusSchedule> schedule = restTemplate.getForEntity("http://BUS-SCHEDULE/api/schedule/" + customer.getScheduleId(), BusSchedule.class);
            BusSchedule scheduleBody = schedule.getBody();
            customer.setBusSchedule(scheduleBody);

            ResponseEntity<Routes> routes = restTemplate.getForEntity("http://ROUTES/api/route/" + customer.getRouteId(), Routes.class);
            Routes routesBody = routes.getBody();
            customer.setRoutes(routesBody);

//            ResponseEntity<Seat> seat = restTemplate.getForEntity("http://BUS-SCHEDULE/api/seat/" + customer.getSeatId(), Seat.class);
//            Seat seatBody = seat.getBody();
//            customer.setSeat(seatBody);

        }
        return byUserId;
    }

    @Override
    public long CountTicketOfCurrentUser(Long userId) {
        return this.customerRepo.countCustomersByUserId(userId);
    }


}
