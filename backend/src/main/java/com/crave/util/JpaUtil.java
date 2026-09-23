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

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}

