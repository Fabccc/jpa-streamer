package com.speedment.jpastreamer.application.standard.internal;

import com.speedment.jpastreamer.application.Streamer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public final class StandardStreamer<E> implements Streamer<E> {

    private final Class<E> entityClass;
    private final EntityManager entityManager;

    public StandardStreamer(final Class<E> entityClass, final String persistenceUnitName) {
        this.entityClass = requireNonNull(entityClass);
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(requireNonNull(persistenceUnitName));
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Stream<E> stream() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final Root<E> root = criteriaQuery.from(entityClass);
        final TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        criteriaQuery.select(root);
        final Stream<E> filmStream = typedQuery.getResultStream();
        return filmStream;
    }
}