package com.microservice.bus.repository;

import com.microservice.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepo extends JpaRepository<Bus,String > {
    long count();
}
