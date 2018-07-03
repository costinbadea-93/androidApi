package androidApi.controller;


import androidApi.dto.UserResponseDTO;
import androidApi.model.Accomodations;
import androidApi.model.Reservations_accomodations;
import androidApi.service.AccomodationService;
import androidApi.service.Reservation_accService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accomodations")
@Api(tags = "accomodations")
public class AccomodationController {

    @Autowired
    AccomodationService accomodationService;

    @Autowired
    Reservation_accService reservation_accService;

    @GetMapping(value = "/getAccomodations")
    @ApiOperation(value = "${AccomodationController.getAll}", response = Accomodations.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<?> getAllAccomodations(
            @RequestParam String country,
            @RequestParam String BeginDate,
            @RequestParam String EndDate,
            @RequestParam int numberOfRooms,
            @RequestParam String roomType
    ) {
        return accomodationService.getAccomodations(country, BeginDate, EndDate, numberOfRooms, roomType);
    }

    @PostMapping(value = "/addReservationAccomodation")
    @ApiOperation(value = "${AccomodationController.addReservationAccomodation}", response = Accomodations.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> addReservationAccomodation(@RequestBody Reservations_accomodations ressAcc,
                                                        @RequestParam int accId,
                                                        @RequestParam int userId) {
        //return reservation_accService
        Reservations_accomodations savedReservation =  reservation_accService.addReservation(ressAcc,accId,userId);
        return ResponseEntity.ok().body(savedReservation.getAccomodation_reservation_id());
    }
}


