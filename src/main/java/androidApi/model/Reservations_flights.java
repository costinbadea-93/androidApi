package androidApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservations_flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flight_reservation_id;
    private String flight_date;
    private int numberOfSeats;
    private int isBusiness;

    @ManyToOne
    private Flights flight;

    @JsonIgnore
    @ManyToOne
    private User user;

    public int getFlight_reservation_id() {
        return flight_reservation_id;
    }

    public void setFlight_reservation_id(int flight_reservation_id) {
        this.flight_reservation_id = flight_reservation_id;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }

    public Flights getFlight() {
        return flight;
    }

    public void setFlight(Flights flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(int isBusiness) {
        this.isBusiness = isBusiness;
    }

}
