package androidApi.dto;

import androidApi.model.Accomodations;
import androidApi.model.Flights;
import androidApi.model.Reservations_accomodations;
import androidApi.model.Reservations_flights;

public class FullReservationDTO {
     private Accomodations accomodations;
     private Flights flightFrom;
     private Flights flightTo;

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
