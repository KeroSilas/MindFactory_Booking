package group3.mindfactory_booking.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

/**
 * A class that is responsible for connecting to the SQL server.
 */

public class DatabaseConnector {

    private final SQLServerDataSource dataSource;

    //Constructor that defines the connection to the SQL server.
    public DatabaseConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("Mindfactory");
        dataSource.setUser("sa");
        dataSource.setPassword("123456");
        dataSource.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}