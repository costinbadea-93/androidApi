package androidApi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class Flights {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String flight_id;
    private String getDeparture_airport_code;
    private String arrival_airport_code;
    private Time departure_time;
    private Time arrival_time;
    private String airline;
    private int seats_economic;
    private int price_economic;
    private int seats_business;
    private double price_business;

    public Cities getArrival_city_id() {
        return arrival_city_id;
    }

    public void setArrival_city_id(Cities arrival_city_id) {
        this.arrival_city_id = arrival_city_id;
    }

    public Cities getDeparture_city_id() {
        return departure_city_id;
    }

    public void setDeparture_city_id(Cities departure_city_id) {
        this.departure_city_id = departure_city_id;
    }

    @ManyToOne
    private Cities arrival_city_id;
    @ManyToOne
    private Cities departure_city_id;

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getGetDeparture_airport_code() {
        return getDeparture_airport_code;
    }

    public void setGetDeparture_airport_code(String getDeparture_airport_code) {
        this.getDeparture_airport_code = getDeparture_airport_code;
    }

    public String getArrival_airport_code() {
        return arrival_airport_code;
    }

    public void setArrival_airport_code(String arrival_airport_code) {
        this.arrival_airport_code = arrival_airport_code;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getSeats_economic() {
        return seats_economic;
    }

    public void setSeats_economic(int seats_economic) {
        this.seats_economic = seats_economic;
    }

    public int getPrice_economic() {
        return price_economic;
    }

    public void setPrice_economic(int price_economic) {
        this.price_economic = price_economic;
    }

    public int getSeats_business() {
        return seats_business;
    }

    public void setSeats_business(int seats_business) {
        this.seats_business = seats_business;
    }

    public double getPrice_business() {
        return price_business;
    }

    public void setPrice_business(double price_business) {
        this.price_business = price_business;
    }
}