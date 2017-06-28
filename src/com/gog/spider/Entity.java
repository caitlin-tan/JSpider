package com.gog.spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gog.spider.util.Config;

public abstract class Entity {
    protected String tableName;
    protected int entityId;
    protected String name;

    private String driverName = "com.mysql.jdbc.Driver";

    protected static Connection connection;

    public Entity() {
        init();
    }

    public abstract int add();

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void init() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(
                    Config.getStringByKey("mysql", "url"),
                    Config.getStringByKey("mysql", "username"),
                    Config.getStringByKey("mysql", "password")
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
