package ru.startseva.dao;

import ru.startseva.models.Cat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ru.startseva.hibernate.Hibernate;
import java.util.List;

public class CatDAOImpl implements CatDAO {
    private final EntityManager entityManager = Hibernate.getEntityManagerFactory().createEntityManager();

    @Override
    public List<Cat> getAllCats() {
        entityManager.getTransaction().begin();
        String jpql = "SELECT c FROM Cat c";
        TypedQuery<Cat> cats = entityManager.createQuery(jpql, Cat.class);
        entityManager.getTransaction().commit();
        return cats.getResultList();
    }

    @Override
    public Cat getCatById(int id) {
        entityManager.getTransaction().begin();
        String jpql = String.format("SELECT c FROM Cat c WHERE CatID = %d", id);
        Cat cat = entityManager.createQuery(jpql, Cat.class).getSingleResult();
        entityManager.getTransaction().commit();
        return cat;
    }

    @Override
    public void addCat(Cat cat) {
        entityManager.getTransaction().begin();
        entityManager.persist(cat);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateCat(Cat cat) {
        entityManager.getTransaction().begin();
        entityManager.merge(cat);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCat(Cat cat) {
        entityManager.getTransaction().begin();
        entityManager.remove(cat);
        entityManager.getTransaction().commit();
    }
}
