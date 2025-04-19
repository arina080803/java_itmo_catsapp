package ru.startseva.hibernate;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class Hibernate {
    @Getter
    private final static EntityManagerFactory entityManagerFactory = initEntityManagerFactory();

    private static EntityManagerFactory initEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ru.startseva.DAO.jpa");
    }

    public static void close() {
        getEntityManagerFactory().close();
    }
}
