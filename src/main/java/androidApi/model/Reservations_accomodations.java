package androidApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class Reservations_accomodations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accomodation_reservation_id;

    private String begin_time;
    private String end_time;
    private String roomtype;
    private int no_of_rooms;


    @ManyToOne
    private Accomodations accomodation;

    @JsonIgnore
    @ManyToOne
    private User user;

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Accomodations getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(Accomodations accomodation) {
        this.accomodation = accomodation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAccomodation_reservation_id() {
        return accomodation_reservation_id;
    }

    public void setAccomodation_reservation_id(int accomodation_reservation_id) {
        this.accomodation_reservation_id = accomodation_reservation_id;
    }

    public String getBegin_time() {
        return begin_time;
    }


    public String getEnd_time() {
        return end_time;
    }


    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }
}
