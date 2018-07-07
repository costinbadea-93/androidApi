package androidApi.dto;

import androidApi.model.*;

public class FullReservationDTO {
     private Accomodations accomodations;
     private Flights flightFrom;
     private Flights flightTo;
     private Roomtypes roomtypes;

    public Roomtypes getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(Roomtypes roomtypes) {
        this.roomtypes = roomtypes;
    }

    public Accomodations getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(Accomodations accomodations) {
        this.accomodations = accomodations;
    }

    public Flights getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(Flights flightFrom) {
        this.flightFrom = flightFrom;
    }

    public Flights getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(Flights flightTo) {
        this.flightTo = flightTo;
    }
}
