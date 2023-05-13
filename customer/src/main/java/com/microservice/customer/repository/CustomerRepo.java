package com.microservice.customer.repository;


import com.microservice.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String > {
//    public Customer findCustomerByCustomerNumber(String number);
//    public Set<Customer> findAllBySeatScheduleId(Long scheduleId);
//    @Query("SELECT c FROM Customer c JOIN c.seat s JOIN s.schedule sch WHERE sch.scheduleId=:scheduleId")
//    Set<Customer>  findCustomersByScheduleId(@Param("scheduleId") Long scheduleId);

    List<Customer> findByScheduleId(String ScheduleId);

    List<Customer> findBySeatId(Long seatId);
    List<Customer> findByUserId(Long userId);

    long count();
}
