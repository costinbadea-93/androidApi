package androidApi.model;

import javax.persistence.*;

@Entity
public class Wishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishes_id;
    private String wish_name;
    @ManyToOne
    private Cities city;

    public int getWishes_id() {
        return wishes_id;
    }

    public void setWishes_id(int wishes_id) {
        this.wishes_id = wishes_id;
    }

    public String getWish_name() {
        return wish_name;
    }

    public void setWish_name(String wish_name) {
        this.wish_name = wish_name;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }
}
