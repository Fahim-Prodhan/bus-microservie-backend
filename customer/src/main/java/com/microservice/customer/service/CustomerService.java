package com.microservice.customer.service;


import com.microservice.customer.model.Customer;
import com.microservice.customer.model.TransactionDetails;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer getCustomerById(Long customerId);
    public void deleteCustomer(Long  customerId);

    public Set<Customer> getAllCustomers();

    public long getTotalCustomers();
    List<Customer> getCustomerOfSchedule(String scheduleId);

//    List<Customer> getCustomerOfSeat(Long seatId);

    List<Customer> getAllCustomerOfCurrentUser(Long userId);

    long CountTicketOfCurrentUser(Long userId);

    TransactionDetails createTransaction(Double amount);

    

//    Customer addCustomerWithSeat(Customer customer, List<Long> seatIds);

//    public Customer getCustomerByNumber(String number);

//    public Set<Customer> getCustomersByScheduleId(Long scheduleId);

//    public Set<Customer> getCustomersByScheduleId(Long scheduleId);
}
