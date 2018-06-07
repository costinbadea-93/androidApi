package androidApi.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;

  @OneToMany(mappedBy = "user")
  private List<Reservations_accomodations> ressAccs;

  @OneToMany(mappedBy = "user")
  private List<Reservations_flights> ressFligh;

  public List<Reservations_accomodations> getRessAccs() {
    return ressAccs;
  }

  public void setRessAccs(List<Reservations_accomodations> ressAccs) {
    this.ressAccs = ressAccs;
  }

  public List<Reservations_flights> getRessFligh() {
    return ressFligh;
  }

  public void setRessFligh(List<Reservations_flights> ressFligh) {
    this.ressFligh = ressFligh;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
