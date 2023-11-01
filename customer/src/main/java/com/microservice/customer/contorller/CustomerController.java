package com.microservice.customer.contorller;

import com.microservice.customer.model.Customer;
import com.microservice.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {



    @Autowired
    private CustomerService customerService;



    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(this.customerService.addCustomer(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
    }


    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(this.customerService.updateCustomer(customer));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        this.customerService.deleteCustomer(customerId);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }

    @GetMapping("/count")
    public long getTotalCustomer() {
        return customerService.getTotalCustomers();
    }



    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<Customer>> getCustomersOfSchedule(@PathVariable String scheduleId){
        List<Customer> customerOfSchedule = this.customerService.getCustomerOfSchedule(scheduleId);
        return ResponseEntity.ok(customerOfSchedule);
    }
    @GetMapping("/seat/{seatId}")
    public ResponseEntity<List<Customer>> getCustomersOfSeat(@PathVariable Long seatId){
        List<Customer> customerOfSchedule = this.customerService.getCustomerOfSeat(seatId);
        return ResponseEntity.ok(customerOfSchedule);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Customer>> getAllCustomerOfCurrentUser(@PathVariable Long userId){
        List<Customer> allCustomerOfCurrentUser = this.customerService.getAllCustomerOfCurrentUser(userId);
        return ResponseEntity.ok(allCustomerOfCurrentUser);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> CountTicketOfCurrentUser(@PathVariable Long userId) {
        long TicketCount = this.customerService.CountTicketOfCurrentUser(userId);
        return ResponseEntity.ok(TicketCount);
    }

    @GetMapping("pay/{amount}")
    public void createTransaction (@PathVariable Double amount) {

    }



}
