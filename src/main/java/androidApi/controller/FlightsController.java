package androidApi.controller;


import androidApi.dto.UserResponseDTO;
import androidApi.model.Accomodations;
import androidApi.model.Cities;
import androidApi.model.Flights;
import androidApi.service.AccomodationService;
import androidApi.service.CityService;
import androidApi.service.FlightsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
@Api(tags = "flights")
public class FlightsController {

    @Autowired
    FlightsService flightsService;

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
}


