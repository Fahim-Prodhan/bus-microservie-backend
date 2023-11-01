package com.microservice.customer.repository;


import com.microservice.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long > {
//    public Customer findCustomerByCustomerNumber(String number);
//    public Set<Customer> findAllBySeatScheduleId(Long scheduleId);
//    @Query("SELECT c FROM Customer c JOIN c.seat s JOIN s.schedule sch WHERE sch.scheduleId=:scheduleId")
//    Set<Customer>  findCustomersByScheduleId(@Param("scheduleId") Long scheduleId);

    List<Customer> findByScheduleId(String ScheduleId);

//    List<Customer> findBySeatId(Long seatId);
    List<Customer> findByUserId(Long userId);

//    @Modifying
//    @Query("UPDATE Customer c SET c.Booked = :isBooked WHERE c.seatId = :seatId")
//    void updateSeatIsBookedStatus(@Param("seatId") String seatId, @Param("isBooked") boolean isBooked);


    long count();
    long countCustomersByUserId(Long userId);
}
