package com.tms.samples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ibmaccess.DB2400;

//import com.ibm.as400.*;
import java.time.*;


public class GetTmsAuditData {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        //  Date date = new Date() ;

        try {
            //	DB2400 box = new DB2400();
            //	DB2400 box = new DB2400(system, userid, password) ;
            //		   	DB2400 box = new DB2400() ;
            //	DB2400 box = new DB2400("daybreak", "scuser10", "scuser10") ;
            //	DB2400 box = new DB2400("sunbeam", "SCJAVA", "RAH4116B") ;

            DB2400 box = new DB2400("galaxy", "MMJAVA", "HAH19195B");
            LocalDate dateonly = LocalDate.now();

            Connection conn = box.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    //					"Select * From MM410SLIB.TMSAUDIT "
                    "Select * from TMSAUDIT"
                            + " Where TAUDDATE >= ? And"
                            + "       TAUDDATE <= ? Order By TAUDDATE,"
                            + " TAPINAME ");

            pstmt.setString(1, dateonly.minusDays(3).toString());
            pstmt.setString(2, dateonly.toString());

            System.out.println("Getting TMS Audit table data from DB2/400..."
                    + ".for Date Range:: " + dateonly.minusDays(3).toString()
                    + " to " + dateonly.toString());

            System.out.println("Doing Query....");
            System.out.println();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())

                System.out.println(
                        rs.getString(1) + "\t" +
                                rs.getString(2) + "\t " +
                                rs.getString(3));

            System.out.println();
            System.out.println("Closing everything....");
            rs.close();
            pstmt.close();
            conn.close();
            System.out.println("Done....!");


            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;

            System.out.println("Total Run Time in Milli seconds : " +
                    elapsedTime / 1000000);


        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("SQL Error...........: " +
                    e.getMessage());
            System.out.println("SQL Error Code......: " +
                    e.getErrorCode());
            System.out.println("SQL Error State()...: " +
                    e.getSQLState());
        }


    }
}

