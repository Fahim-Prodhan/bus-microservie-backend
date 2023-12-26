package com.microservice.routes.contorller;


import com.microservice.routes.model.Routes;
import com.microservice.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("api/route")
public class RouteController {



    @Autowired
    private RoutesService routesService;

    @PostMapping
    public ResponseEntity<Routes> addRoute(@RequestBody Routes routes) {

        return ResponseEntity.ok(this.routesService.addRoute(routes));
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Routes> getRoute(@PathVariable("routeId") String routeId) {
        return ResponseEntity.ok(this.routesService.getRoute(routeId));
    }

    @GetMapping
    public ResponseEntity<Set<Routes>> getAllRoutes() {
        Set<Routes> allRoutes = this.routesService.getAllRoutes();
        return ResponseEntity.ok(allRoutes);
    }

    @PutMapping
    public ResponseEntity<Routes> updateRoute (@RequestBody Routes routes) {
        return ResponseEntity.ok(this.routesService.updateRoute(routes));
    }

    @DeleteMapping("/{routeId}")
    public void deleteRoute(@PathVariable("routeId") String routeId) {
        this.routesService.deleteRoute(routeId);
    }

    @GetMapping("/schedule/{sId}")
    public ResponseEntity<List<Routes>> getRoutesByScheduleId(@PathVariable String sId) {
        List<Routes> routesByScheduleId = routesService.getRoutesByScheduleId(sId);
        return ResponseEntity.ok(routesByScheduleId);
    }



    @GetMapping("/count")
    public long getCountOfRoutes() {
        return this.routesService.getRouteCount();
    }

    @DeleteMapping("schedule/{sId}")
    public void deleteRouteWithScheduleId(@PathVariable String sId) {
        routesService.deleteAllByScheduleId(sId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Routes>> searchRoutes(
            @RequestParam(name = "departure", required = false) String departure,
            @RequestParam(name = "destination", required = false) String destination,
            @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date
    ){

        List<Routes> routesList = null;

        if (departure != null && date != null){
            routesList = this.routesService.searchRoutes(departure,destination, date);
        }
        if (routesList == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(routesList);
        }
    }
    @GetMapping("/searchByDate")
    public ResponseEntity<List<Routes>> searchByDate (
            @RequestParam(name = "date",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        List<Routes> routes = null;
        if (date != null) {
        routes = this.routesService.searchRoutesByDate(date);
        }
        if (routes == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(routes);
        }
    }
}
