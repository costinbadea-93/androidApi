package androidApi.dto;

import androidApi.model.Reservations_accomodations;
import androidApi.model.Reservations_flights;

public class FullReservationDTO {
    private Reservations_accomodations reservations_accomodations;
    private Reservations_flights toFlightsReservations;
    private Reservations_flights fromFlightsReservations;

    public FullReservationDTO(Reservations_accomodations reservations_accomodations, Reservations_flights toFlightsReservations, Reservations_flights fromFlightsReservations) {
        this.reservations_accomodations = reservations_accomodations;
        this.toFlightsReservations = toFlightsReservations;
        this.fromFlightsReservations = fromFlightsReservations;
    }

    public Reservations_accomodations getReservations_accomodations() {
        return reservations_accomodations;
    }

    public void setReservations_accomodations(Reservations_accomodations reservations_accomodations) {
        this.reservations_accomodations = reservations_accomodations;
    }

    public Reservations_flights getToFlightsReservations() {
        return toFlightsReservations;
    }

    public void setToFlightsReservations(Reservations_flights toFlightsReservations) {
        this.toFlightsReservations = toFlightsReservations;
    }

    public Reservations_flights getFromFlightsReservations() {
        return fromFlightsReservations;
    }

    public void setFromFlightsReservations(Reservations_flights fromFlightsReservations) {
        this.fromFlightsReservations = fromFlightsReservations;
    }
}
