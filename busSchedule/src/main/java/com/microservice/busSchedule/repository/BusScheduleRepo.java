package com.microservice.busSchedule.repository;


import com.microservice.busSchedule.model.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusScheduleRepo extends JpaRepository<BusSchedule,String> {
//    @Query("SELECT s FROM Seat s JOIN FETCH s.schedule JOIN FETCH s.customer WHERE s.schedule.scheduleId = :scheduleId")
//    List<Seat> findSeatsByScheduleId(@Param("scheduleId") Long ScheduleId);

    long count();


}
