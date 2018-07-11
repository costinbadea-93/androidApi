package androidApi.dto;

import androidApi.model.Reservations_accomodations;
import androidApi.model.Reservations_flights;

public class ItineraryDTO {
    private Reservations_flights reservations_flights;
    private Reservations_accomodations reservations_accomodations;

    public Reservations_flights getReservations_flights() {
        return reservations_flights;
    }

    public void setReservations_flights(Reservations_flights reservations_flights) {
        this.reservations_flights = reservations_flights;
    }

    public Reservations_accomodations getReservations_accomodations() {
        return reservations_accomodations;
    }

    public void setReservations_accomodations(Reservations_accomodations reservations_accomodations) {
        this.reservations_accomodations = reservations_accomodations;
    }
}
