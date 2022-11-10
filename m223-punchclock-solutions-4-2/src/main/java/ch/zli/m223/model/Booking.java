package ch.zli.m223.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Entity
@NamedQueries({
    @NamedQuery(name = "Booking.findBookingById", query = "SELECT u FROM Booking u WHERE u.bookingId = :id")
  })
@Table(name = "Booking")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long bookingId;

  @Column(nullable = false)
  private LocalDate date;
  
  @Column(nullable = false)
  private Boolean isFullDay;

  @Column(nullable = false)
  private Boolean active;

  @Column(nullable = false)
  private Boolean isAccepted;

  @ManyToOne
  @JoinColumn(name="userId", nullable=false)
  private ApplicationUser user;

  
  @OneToOne(mappedBy = "booking")
    private Status status;


  public Booking() {}


public Long getBookingId() {
    return bookingId;
}


public void setBookingId(Long bookingId) {
    this.bookingId = bookingId;
}


public LocalDate getDate() {
    return date;
}


public void setDate(LocalDate date) {
    this.date = date;
}


public Boolean getIsFullDay() {
    return isFullDay;
}


public void setIsFullDay(Boolean isFullDay) {
    this.isFullDay = isFullDay;
}


public Boolean getActive() {
    return active;
}


public void setActive(Boolean active) {
    this.active = active;
}


public Boolean getIsAccepted() {
    return isAccepted;
}


public void setIsAccepted(Boolean isAccepted) {
    this.isAccepted = isAccepted;
}


public ApplicationUser getUser() {
    return user;
}


public void setUser(ApplicationUser user) {
    this.user = user;
}


public Status getStatus() {
    return status;
}


public void setStatus(Status status) {
    this.status = status;
}




}

