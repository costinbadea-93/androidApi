package androidApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Accomodations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accomodation_id;
    private String accomodation_name;
    private int city_id;
    private double latitude;
    private double longitude;
    private double rating;
    private int star_number;

    public int getAccomodation_id() {
        return accomodation_id;
    }

    public void setAccomodation_id(int accomodation_id) {
        this.accomodation_id = accomodation_id;
    }

    public String getAccomodation_name() {
        return accomodation_name;
    }

    public void setAccomodation_name(String accomodation_name) {
        this.accomodation_name = accomodation_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStar_number() {
        return star_number;
    }

    public void setStar_number(int star_number) {
        this.star_number = star_number;
    }
}
