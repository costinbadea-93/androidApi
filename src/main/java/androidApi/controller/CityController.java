package androidApi.controller;


import androidApi.dto.FullReservationDTO;
import androidApi.dto.UserResponseDTO;
import androidApi.model.*;
import androidApi.service.AccomodationService;
import androidApi.service.CityService;
import androidApi.service.Views_service;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cities")
@Api(tags = "cities")
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    Views_service views_service;

    @GetMapping(value = "/getCities")
    @ApiOperation(value = "${CityController.getAll}", response = Cities.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Cities> getAllCities() {
        return cityService.getCities();
    }

    @PostMapping(value = "/getWishesByCountry")
    @ApiOperation(value = "${CityController.getWishesByCountry}", response = Wishes.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Wishes> getWishesByCountry(@RequestParam String countryName,
                                           @RequestParam int priece) {
        return cityService.getWishesByCountry(countryName, priece);
    }

    @GetMapping(value = "/viewData")
    @ApiOperation(value = "${CityController.view}", response = View_Cities.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<View_Cities> getViewCities() {
        return views_service.getViewCitiesData();
    }

    @GetMapping(value = "/getParsedData")
    @ApiOperation(value = "${CityController.view}", response = FullReservationDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<FullReservationDTO> getParsedData(
            @RequestParam String DepartureCountry,
            @RequestParam String ArrivalCountry,
            @RequestParam String BeginDate,
            @RequestParam String EndDate,
            @RequestParam double budget,
            @RequestParam int trackHistory,
            @RequestParam int isBusiness,
            @RequestParam int numberOfRooms,
            @RequestParam String roomType,
            @RequestParam int numberOfSeats
            ) {
        return cityService.getParsedData(DepartureCountry, ArrivalCountry, BeginDate, EndDate,
                budget, trackHistory, isBusiness, numberOfRooms, roomType,numberOfSeats);
    }
}


