package com.microservice.routes.service;


import com.microservice.routes.model.Routes;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface RoutesService {

    public Routes addRoute(Routes routes);
    public Routes updateRoute(Routes routes);
    public Routes getRoute(String routeId);
    public Set<Routes> getAllRoutes();
    public void deleteRoute(String routeId);

    List<Routes> getRoutesByScheduleId(String sId);
    public long getRouteCount();

//    void deleteWithScheduleId(String sId);

    void deleteAllByScheduleId(String sId);

//    List<Routes> searchRoutesDeparture(String depart,  Date date);
//    List<Routes> searchRoutesDestination(String destination,Date date);

    List<Routes> searchRoutes(String depart, String destination, Date date);
    List<Routes> searchRoutesByDate(Date date);

}
