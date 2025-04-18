package ru.startseva.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ru.startseva.hibernate.Hibernate;
import ru.startseva.models.Owner;

import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {
    private final EntityManager entityManager = Hibernate.getEntityManagerFactory().createEntityManager();

    @Override
    public List<Owner> getAllOwners() {
        entityManager.getTransaction().begin();
        String jpql = "SELECT c FROM Owner c";
        TypedQuery<Owner> catOwners = entityManager.createQuery(jpql, Owner.class);
        entityManager.getTransaction().commit();
        return catOwners.getResultList();
    }

    @Override
    public Owner getOwnerById(int id) {
        entityManager.getTransaction().begin();
        String jpql = String.format("SELECT c FROM Owner c WHERE OwnerID = %d", id);
        Owner owner = entityManager.createQuery(jpql, Owner.class).getSingleResult();
        entityManager.getTransaction().commit();
        return owner;
    }

    @Override
    public void addOwner(Owner owner) {
        entityManager.getTransaction().begin();
        entityManager.persist(owner);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateOwner(Owner owner) {
        entityManager.getTransaction().begin();
        entityManager.merge(owner);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteOwner(Owner owner) {
        entityManager.getTransaction().begin();
        entityManager.remove(owner);
        entityManager.getTransaction().commit();
    }
}
