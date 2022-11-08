package ch.zli.m223.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Table(name="User")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long userId;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;
  
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean active;

  @Column(nullable = false)
  private Boolean isAdmin;

  @OneToMany(mappedBy="user")
  private Set<Booking> booking;

public Long getUserId() {
    return userId;
}

public void setUserId(Long userId) {
    this.userId = userId;
}

public String getFirstname() {
    return firstname;
}

public void setFirstname(String firstname) {
    this.firstname = firstname;
}

public String getLastname() {
    return lastname;
}

public void setLastname(String lastname) {
    this.lastname = lastname;
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

public Boolean getActive() {
    return active;
}

public void setActive(Boolean active) {
    this.active = active;
}

public Boolean getIsAdmin() {
    return isAdmin;
}

public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
}

public Set<Booking> getBooking() {
    return booking;
}

public void setBooking(Set<Booking> booking) {
    this.booking = booking;
}




}

