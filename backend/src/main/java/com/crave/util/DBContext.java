package com.crave.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "QuanLyDatDoAn";
    private static final String USER = "root";
    private static final String PASS = "12345";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
                + "?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        return DriverManager.getConnection(url, USER, PASS);
    }
}
