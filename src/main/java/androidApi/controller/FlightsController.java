package androidApi.controller;


import androidApi.dto.UserResponseDTO;
import androidApi.model.*;
import androidApi.service.AccomodationService;
import androidApi.service.CityService;
import androidApi.service.FlightsService;
import androidApi.service.ReservationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@Api(tags = "flights")
public class FlightsController {

    @Autowired
    FlightsService flightsService;

    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/getFlights")
    @ApiOperation(value = "${FlightsController.getAll}", response = Flights.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Flights> getAllFlights() {
        return flightsService.getFlights();
    }

    @PostMapping(value = "/addFlightReservation")
    @ApiOperation(value = "${FlightsController.addFlightReservation}", response = FlightsController.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> addReservationFlight(@RequestBody Reservations_flights resFlight,
                                                        @RequestParam int flightId,
                                                        @RequestParam int userId) {
        //return reservation_accService
        Reservations_flights savedReservation =  reservationService.addResFlight(resFlight,flightId,userId);
        return ResponseEntity.ok().body(savedReservation.getFlight_reservation_id());
    }
}


