package androidApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;
    private String city_name;
    private String country_code;
    private double latitude;
    private double longitude;

    public List<Accomodations> getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(List<Accomodations> accomodations) {
        this.accomodations = accomodations;
    }

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Accomodations> accomodations;

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    @ManyToOne
    @JsonIgnore
    private Countries country;

    @OneToMany(mappedBy = "arrival_city_id", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flights> arrival_fligths;

    @OneToMany(mappedBy = "departure_city_id", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flights> departure_flights;



    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
