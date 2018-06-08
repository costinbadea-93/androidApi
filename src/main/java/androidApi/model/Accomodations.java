package androidApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Accomodations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accomodation_id;
    private String accomodation_name;
    private double latitude;
    private double longitude;
    private double rating;
    private int star_number;

    @JsonIgnore
    public Cities getAcc_city() {
        return city;
    }

    public void setAcc_city(Cities city) {
        this.city = city;
    }

    @JsonIgnore
    @ManyToOne
    private Cities city;

    @JsonIgnore
    @OneToMany(mappedBy = "accomodation")
    private List<Reservations_accomodations> rezAccs;

    @JsonIgnore
    @ManyToOne
    private Roomtypes roomType;

    @JsonIgnore
    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

//    public List<Reservations_accomodations> getRacc() {
//        return racc;
//    }
//
//    public void setRacc(List<Reservations_accomodations> racc) {
//        this.racc = racc;
//    }
    @JsonIgnore
    public Roomtypes getRoomType() {
        return roomType;
    }

    public void setRoomType(Roomtypes roomType) {
        this.roomType = roomType;
    }

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
