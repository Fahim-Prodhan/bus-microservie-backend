package com.microservice.routes.repository;


import com.microservice.routes.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface RoutesRepo extends JpaRepository<Routes, String> {
    List<Routes> getRoutesByScheduleId(String sId);
//    public Set<Routes> findAllBySchedule();
    long count();
//    void deleteByScheduleId(String sId);

    void deleteByScheduleId(String sId);

//    List<Routes> findByDepartureAndDate(String depart, Date date);
//    List<Routes> findByDestinationAndDate(String destination, Date date);

    List<Routes> findByDepartureAndDestinationAndDate(String depart, String destination, Date date);
    List<Routes> findAllByDate(Date date);
}
