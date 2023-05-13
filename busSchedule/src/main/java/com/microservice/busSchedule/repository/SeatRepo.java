package com.microservice.busSchedule.repository;

import com.microservice.busSchedule.model.BusSchedule;
import com.microservice.busSchedule.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface SeatRepo extends JpaRepository<Seat, Long> {
//    Set<Seat> findByBusSchedule(BusSchedule schedule);
    public Set<Seat> findAllByBooked(Boolean b);
    long count();

    @Query(value = "SELECT s FROM BusSchedule s WHERE s.scheduleId = :sId")
    BusSchedule getAllByScheduleId(@Param("sId") String sId);

    List<Seat> findBySeatIdIn(List<Long> ids);

    List<Seat> findByScheduleId(String sId);

//    void deleteSeatByScheduleId(String sId);

    void deleteByScheduleId(String sId);
}


