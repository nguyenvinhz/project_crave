package com.crave.config;

import com.zaxxer.hikari.HikariDataSource;

public final class HikariDataSourceProvider {
    private HikariDataSourceProvider() {
    }

    public static HikariDataSource createDataSource() {
        throw new UnsupportedOperationException("TODO: configure HikariCP datasource");
    }
}

