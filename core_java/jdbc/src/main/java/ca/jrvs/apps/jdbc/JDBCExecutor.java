package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {

  public static final Logger LOGGER = LoggerFactory.getLogger(JDBCExecutor.class);

  public static void main(String[] args) {

    BasicConfigurator.configure();

    DatabaseConnectionManager dmc = new DatabaseConnectionManager
        ("localhost", "hplussport", "andres", "pass");

    try {
      Connection connection = dmc.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      Customer customer = new Customer();
      customer.setFirstName("Jhon");
      customer.setLastName("Adams");
      customer.setEmail("jadams.wh.gov");
      customer.setAddress("1234 Main St");
      customer.setCity("Arlingnton");
      customer.setState("VA");
      customer.setPhone("(555) 555 9845");
      customer.setZipCode("01234");

      Customer dbCustomer = customerDAO.create(customer);
      System.out.println(dbCustomer);
      dbCustomer = customerDAO.findById(dbCustomer.getId());
      System.out.println(dbCustomer);
      dbCustomer.setEmail("john.adams@wh.gov");
      dbCustomer = customerDAO.update(dbCustomer);
      System.out.println(dbCustomer);
      customerDAO.delete(dbCustomer.getId());
    } catch (SQLException e) {
      LOGGER.error("ERROR: Connecting or SQL statement", e);
    }
  }

}
