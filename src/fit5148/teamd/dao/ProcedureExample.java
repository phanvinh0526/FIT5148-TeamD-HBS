/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VinhPQ
 */
public class ProcedureExample {

    Connection conn;

    public ProcedureExample() {

        try {
            conn = OracleDBConnectionUtil.getInstance().getConnectionB();
        } catch (SQLException ex) {
            Logger.getLogger(ProcedureExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ProcedureExample main = new ProcedureExample();
        //main.runStoreProcedure();
        main.insert()
;    }

    private void runStoreProcedure() {

        boolean status = true;
        String eml = "";
        PreparedStatement stmt = null;
        ResultSet rs;
        CallableStatement callableStatement = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("select email from  personal_details  where pd_id=? ");
            stmt.setInt(1, 1);//1 specifies the first parameter in the query  

            rs = stmt.executeQuery();

            while (rs.next()) {

                eml = rs.getString("email");

            }

            rs.close();
            stmt.close();

            System.out.println("Customer old email is " + eml);

            String updateCustomerEmail = "{call  update_cust_email(?,?)}";
            callableStatement = conn.prepareCall(updateCustomerEmail);
            callableStatement.setString(1, "test@test2.com");
            callableStatement.setInt(2, 1);
            // execute stored procedure
            if (callableStatement.executeUpdate() == 0) {
                status = false;
            } else {
                System.out.println("Procedure called successfully");
            }
            if (!status) {
                conn.rollback();
            } else {
                conn.commit();
            }
            callableStatement.close();

            stmt = conn.prepareStatement("select email from  personal_details  where pd_id=? ");
            stmt.setInt(1, 1);

            rs = stmt.executeQuery();

            while (rs.next()) {

                eml = rs.getString("email");

            }

            rs.close();
            stmt.close();

            System.out.println("Customer new email is " + eml);

            conn.close();
        } catch (SQLException e) {
            try {
                if (stmt != null & !stmt.isClosed()) {
                    stmt.close();
                }
                if (callableStatement != null & !callableStatement.isClosed()) {
                    callableStatement.close();
                }
                conn.rollback();
                conn.close();
            } catch (SQLException ex2) {
                ex2.printStackTrace();

            }
            e.printStackTrace();
        }

    }

    public Integer insert() {
        try {
            Statement sm = conn.createStatement();
            //  Get Primary key from Sequence first
            String sql_0 = "SELECT BOOKING_SEQ.NEXTVAL FROM dual";
            Integer primaryKey = -1;
            ResultSet rs = sm.executeQuery(sql_0);
            if (rs.next()) {
                primaryKey = rs.getInt(1);
            }
            rs.close();
            sm.close();
            //  Insert into Personal_Details
            //String sql_1 = "INSERT INTO BOOKING(BOOK_ID, CHECK_IN, CHECK_OUT, CONTACT_P, "
             //       + "CONTACT_EML, TOT_AMT, PAY_STATUS, CUST_ID, BOOK_DATE, HOTEL_ID) VALUES(?,?,?,?,?,?,?,?,?,?)";
             String sql_1= "INSERT INTO BOOKING (BOOK_ID,CHECK_IN,CHECK_OUT, CONTACT_P, CONTACT_EML, TOT_AMT, PAY_STATUS, CUST_ID, BOOK_DATE,HOTEL_ID)" +
" VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql_1);
            ps.setInt(1, primaryKey);
            ps.setDate(2,  new java.sql.Date(new Date().getTime())); // Must convert to sql.date from util.date
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            ps.setString(4, "123123");
            ps.setString(5, "asvarun@ss.com");
            ps.setFloat(6, 1234);
            ps.setString(7, "PAID");
            ps.setInt(8, 1);
            ps.setDate(9, new java.sql.Date(new Date().getTime()));
            ps.setInt(10, 1);
           
            System.out.println(sql_1);
            System.out.println(primaryKey);
            if (ps.executeUpdate() == 0) {
                 ps.close();
                 conn.rollback();
                return -1;
            } else {
                 ps.close();
                conn.commit();
            }
            conn.close();

            //  Insert into Guest
            System.out.println("fit5148.teamd.dao.ProcedureExample.insert()");
            // System.out.println("result = "+m);
//            if (m > 0) {
//                conn.commit();
//                 conn.close();
//                return primaryKey;
//            } else {
//                conn.rollback();
//                 conn.close();
//                return -1;
//            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return 1;
    }

}
