package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

  private final String url;
  private final Properties properties;

  public DatabaseConnectionManager(String host, String dbName, String usrName, String psswrd) {
    this.url = "jdbc:postgresql://"+host+"/"+dbName;
    this.properties = new Properties();
    this.properties.setProperty("user", usrName);
    this.properties.setProperty("password", psswrd);
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(this.url, this.properties);
  }

}
