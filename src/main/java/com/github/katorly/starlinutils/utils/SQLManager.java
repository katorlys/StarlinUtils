package com.github.katorly.starlinutils.utils;

import java.sql.*;

import com.github.katorly.starlinutils.StarlinUtils;

import org.bukkit.configuration.file.FileConfiguration;

public class SQLManager {
    public static Connection getNewConnection() {
        FileConfiguration config = StarlinUtils.config.getConfig();
        String host = config.getString("mysql.host");
        String port = config.getString("mysql.port");
        String user = config.getString("mysql.user");
        String password = config.getString("mysql.password");
        String database = config.getString("mysql.database");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}
