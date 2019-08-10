package com.ibmaccess;

import java.sql.SQLException;
//import java.util.* ;
//import com.ibm.as400.*;

public class TestConnectToAs400 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("Testing DB2/400 connection....");
        try {
            //	DB2400 box = new DB2400();
            //	DB2400 box = new DB2400(system, userid, password) ;
            //	DB2400 box = new DB2400("cosmos", "mmuser9", "mmuser9") ;
            //	DB2400 box = new DB2400("daybreak", "scuser10", "scuser10") ;
            //	DB2400 box = new DB2400("sunbeam", "SCJAVA", "RAH4116B") ;
            DB2400 box = new DB2400("galaxy", "MMJAVA", "HAH19195B") ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Connected to DB2/400 Ok..!!");

    }
    //System.exit(0) ;
}
