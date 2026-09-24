package com.crave.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {
    private static EntityManagerFactory entityManagerFactory;

    private JpaUtil() {
    }

    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("QuanLyDatDoAnPU");
    }

    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            init();
        }
        return entityManagerFactory;
    }

    public static jakarta.persistence.EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static synchronized void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}

