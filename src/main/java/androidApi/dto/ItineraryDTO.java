package androidApi.dto;

import androidApi.model.Reservations_accomodations;
import androidApi.model.Reservations_flights;

public class ItineraryDTO {
    private Reservations_flights reservations_flights_from;
    private Reservations_flights reservations_flights_to;

    public Reservations_flights getReservations_flights_from() {
        return reservations_flights_from;
    }

    public void setReservations_flights_from(Reservations_flights reservations_flights_from) {
        this.reservations_flights_from = reservations_flights_from;
    }

    public Reservations_flights getReservations_flights_to() {
        return reservations_flights_to;
    }

    public void setReservations_flights_to(Reservations_flights reservations_flights_to) {
        this.reservations_flights_to = reservations_flights_to;
    }

    private Reservations_accomodations reservations_accomodations;


    public Reservations_accomodations getReservations_accomodations() {
        return reservations_accomodations;
    }

    public void setReservations_accomodations(Reservations_accomodations reservations_accomodations) {
        this.reservations_accomodations = reservations_accomodations;
    }
}
