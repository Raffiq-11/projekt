package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;

@ApplicationScoped
public class BookingService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Booking createBooking(Booking booking) {
        return entityManager.merge(booking);
    }

    @Transactional
    public void deleteBooking(Long id) {
        var entity = entityManager.find(Booking.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        booking.setBookingId(id);
        return entityManager.merge(booking);
    }

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Booking", Booking.class);
        return query.getResultList();
    }

    public Optional<Booking> findBookingById(Long id) {
        return entityManager
                .createNamedQuery("Booking.findBookingById", Booking.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }
}