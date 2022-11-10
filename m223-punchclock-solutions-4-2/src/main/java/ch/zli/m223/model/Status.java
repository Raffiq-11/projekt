package ch.zli.m223.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@NamedQueries({
    @NamedQuery(name = "Status.findStatusById", query = "SELECT u FROM User u WHERE u.statusId = :id")
  })
@Table(name = "status")
public class Status {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long statusId;

  @Column(nullable = false)
  private Boolean isConfirmed;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "bookingId", referencedColumnName = "bookingId")
  private Booking booking;

public Long getStatusId() {
    return statusId;
}

public void setStatusId(Long statusId) {
    this.statusId = statusId;
}

public Boolean getIsConfirmed() {
    return isConfirmed;
}

public void setIsConfirmed(Boolean isConfirmed) {
    this.isConfirmed = isConfirmed;
}

public Booking getBooking() {
    return booking;
}

public void setBooking(Booking booking) {
    this.booking = booking;
}

  

}


