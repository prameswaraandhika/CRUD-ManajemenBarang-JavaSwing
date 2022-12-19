package com.mycompany.aplikasimanajemenbarangsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prameswara <prameswaara@gmail.com>
 */
public class ConnectionManager {

    private static String user = "postgres";
    private static String password = "dikatampan";

    public Connection getConnection(String dbServer) {
        String driver = "org.postgresql.Driver";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbServer, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, null, e);
        }
        return con;
    }

}
