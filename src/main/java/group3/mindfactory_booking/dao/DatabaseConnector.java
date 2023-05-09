package group3.mindfactory_booking.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

/**
 * A class that is responsible for connecting to the SQL server.
 */

public class DatabaseConnector {

    private static DatabaseConnector instance;

    private final SQLServerDataSource dataSource;

    //Constructor that defines the connection to the SQL server.
    private DatabaseConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("localhost"); // 10.176.111.34
        dataSource.setDatabaseName("MindFactory"); // MindFactory_
        dataSource.setUser("sa"); // CSe2022t_t_3
        dataSource.setPassword("12344321"); // CSe2022tT3#
        dataSource.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }
}