package ch.zli.m223.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import ch.zli.m223.enums.Duration;

@Entity
@Table(name = "booking")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long bookingId;


  @Column(nullable = false)
  private LocalDate date;
  
  @Column(nullable = false)
  private Duration isFullDay;

  @Column(nullable = false)
  private Boolean active;

  @Column(nullable = false)
  private Boolean isAccepted;

  @ManyToOne
  @JoinColumn(name="userId", nullable=false)
  private User user;

  
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



public Boolean getActive() {
    return active;
}

public void setActive(Boolean active) {
    this.active = active;
}

public Duration getIsFullDay() {
    return isFullDay;
}

public void setIsFullDay(Duration isFullDay) {
    this.isFullDay = isFullDay;
}

public Boolean getIsAccepted() {
    return isAccepted;
}

public void setIsAccepted(Boolean isAccepted) {
    this.isAccepted = isAccepted;
}


}
