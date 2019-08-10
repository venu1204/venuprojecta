package com.ibmaccess;

import java.sql.*;

public class DB2400 {

    private static boolean registered = false;
    public static boolean nativeDriver = false;
    private Connection conn = null;

    public DB2400(String systemName, String userId, String password)
            throws SQLException {

        String connectURL = null;

        if (!registered) {

            String osName = System.getProperty("os.name");
            String driverName = null;
            if (osName.equalsIgnoreCase("OS/400")) {
                driverName = "com.ibm.db2.jdbc.app.DB2Driver";
                connectURL = "jdbc:db2";
                nativeDriver = true;
            } else {
                driverName = "com.ibm.as400.access.AS400JDBCDriver";
                connectURL = "jdbc:as400";
            }

            try {
                Driver driver = (Driver)
                        (Class.forName(driverName).newInstance());
                DriverManager.registerDriver(driver);
            } catch (Exception ex) {
                throw new SQLException(ex.getMessage());

            }
            registered = true;
        }
        // connect to the database
        if (systemName != null)
            //	connectURL += "://" + systemName ;

            switch (systemName.toUpperCase()) {
                case "COSMOS":
                    connectURL += "://" + systemName +
                            ";libraries=MM410SLIB,*LIBL" +
                            ";naming=system;errors=full";
                case "DAYBREAK":
                    connectURL += "://" + systemName +
                            ";libraries=SC410SLIB,*LIBL" +
                            ";naming=system;errors=full";
                case "GALAXY":
                    connectURL += "://" + systemName +
                            ";libraries=MMBASLIB,*LIBL" +
                            ";naming=system;errors=full";
                case "SUNBEAM":
                    connectURL += "://" + systemName +
                            ";libraries=MMBASLIB,*LIBL" +
                            ";naming=system;errors=full";
            }

        else if (nativeDriver)
            connectURL += "://localhost";
        if (userId != null)
            conn = DriverManager.getConnection(connectURL, userId, password);
        else
            conn = DriverManager.getConnection(connectURL);

    }// end constructor

    public DB2400() throws SQLException {
        this(null, null, null);
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }


}
