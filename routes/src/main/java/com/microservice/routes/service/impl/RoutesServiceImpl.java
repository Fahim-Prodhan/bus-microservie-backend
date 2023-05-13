package com.microservice.routes.service.impl;


import com.microservice.routes.model.Routes;
import com.microservice.routes.repository.RoutesRepo;
import com.microservice.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    private RoutesRepo routesRepo;

    @Override
    public Routes addRoute(Routes routes) {
        routes.setRouteId(UUID.randomUUID().toString());
        return this.routesRepo.save(routes);
    }

    @Override
    public Routes updateRoute(Routes routes) {
        return this.routesRepo.save(routes);
    }

    @Override
    public Routes getRoute(String routeId) {
        return this.routesRepo.findById(routeId).get();
    }

    @Override
    public Set<Routes> getAllRoutes() {
        return new HashSet<>(this.routesRepo.findAll());
    }

    @Override
    public void deleteRoute(String  routeId) {
        this.routesRepo.deleteById(routeId);
    }

    @Override
    public List<Routes> getRoutesByScheduleId(String sId) {
        List<Routes> routesByScheduleId = routesRepo.getRoutesByScheduleId(sId);
        return routesByScheduleId;
    }

    @Transactional
    @Override
    public void deleteAllByScheduleId(String sId) {
        routesRepo.deleteByScheduleId(sId);
    }

    @Override
    public List<Routes> searchRoutes(String depart, String destination, Date date) {
        List<Routes> byDepartureAndDestinationAndDate = this.routesRepo.findByDepartureAndDestinationAndDate(depart, destination, date);
        return byDepartureAndDestinationAndDate;
    }

//    @Override
//    public List<Routes> searchRoutesDeparture(String depart, Date date) {
//        List<Routes> byDepartureAndDate = this.routesRepo.findByDepartureAndDate(depart, date);
//        return byDepartureAndDate;
//    }
//
//    @Override
//    public List<Routes> searchRoutesDestination(String destination, Date date) {
//        List<Routes> byDestinationAndDate = this.routesRepo.findByDestinationAndDate(destination, date);
//        return byDestinationAndDate;
//    }


//    @Override
//    public void deleteWithScheduleId(String sId) {
//        List<Routes> routesList = routesRepo.getRoutesByScheduleId(sId);
//        for (Routes rs: routesList) {
//            routesRepo.deleteByScheduleId(String.valueOf(rs));
//        }
//    }

    @Override
    public long getRouteCount() {
        return this.routesRepo.count();
    }
}
