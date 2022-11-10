package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Status;

@ApplicationScoped
public class StatusService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Status createStatus(Status status) {
        return entityManager.merge(status);
    }

    @Transactional
    public void deleteStatus(Long id) {
        var entity = entityManager.find(Status.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Status updateStatus(Long id, Status status) {
        status.setStatusId(id);
        return entityManager.merge(status);
    }

    public List<Status> findAll() {
        var query = entityManager.createQuery("FROM Status", Status.class);
        return query.getResultList();
    }

    public Optional<Status> findStatusById(Long id) {
        return entityManager
                .createNamedQuery("Status.findStatusById", Status.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }
}