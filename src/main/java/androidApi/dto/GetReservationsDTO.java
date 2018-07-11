package androidApi.dto;

import androidApi.model.Reservations_accomodations;
import androidApi.model.Reservations_flights;

import java.util.List;

public class GetReservationsDTO {
    private List<Reservations_accomodations> reservationsAccomodations;
    private List<Reservations_flights> reservations_flights;
    private List<ItineraryDTO> itineraryDTOS;

    public List<ItineraryDTO> getItineraryDTOS() {
        return itineraryDTOS;
    }

    public void setItineraryDTOS(List<ItineraryDTO> itineraryDTOS) {
        this.itineraryDTOS = itineraryDTOS;
    }

    public List<Reservations_accomodations> getReservationsAccomodations() {
        return reservationsAccomodations;
    }

    public void setReservationsAccomodations(List<Reservations_accomodations> reservationsAccomodations) {
        this.reservationsAccomodations = reservationsAccomodations;
    }

    public List<Reservations_flights> getReservations_flights() {
        return reservations_flights;
    }

    public void setReservations_flights(List<Reservations_flights> reservations_flights) {
        this.reservations_flights = reservations_flights;
    }

}
