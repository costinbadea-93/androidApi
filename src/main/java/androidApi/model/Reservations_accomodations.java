package androidApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Reservations_accomodations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accomodation_reservation_id;
    private int accomodation_id;
    private Time begin_time;
    private Time end_time;
    private String roomtype;
    private int no_of_rooms;
    private int user_id;

    public int getAccomodation_reservation_id() {
        return accomodation_reservation_id;
    }

    public void setAccomodation_reservation_id(int accomodation_reservation_id) {
        this.accomodation_reservation_id = accomodation_reservation_id;
    }

    public int getAccomodation_id() {
        return accomodation_id;
    }

    public void setAccomodation_id(int accomodation_id) {
        this.accomodation_id = accomodation_id;
    }

    public Time getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Time begin_time) {
        this.begin_time = begin_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
