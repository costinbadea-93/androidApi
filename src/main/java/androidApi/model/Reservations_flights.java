package androidApi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservations_flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flight_reservation_id;
    private Date flight_date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Flights flight;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public int getFlight_reservation_id() {
        return flight_reservation_id;
    }

    public void setFlight_reservation_id(int flight_reservation_id) {
        this.flight_reservation_id = flight_reservation_id;
    }

    public Date getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(Date flight_date) {
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
}