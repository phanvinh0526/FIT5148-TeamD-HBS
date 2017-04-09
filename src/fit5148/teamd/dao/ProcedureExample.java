/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.dao.OracleDBConnectionUtil;
import fit5148.teamd.pojo.CustomerPOJO;
import fit5148.teamd.pojo.PersonalDetailsPOJO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        main.runStoreProcedure();

    }

    private void runStoreProcedure() {

        boolean status = true;
        String eml = "";
        PreparedStatement stmt=null;
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
            }
            else
            System.out.println("Procedure called successfully");
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
            try{
                   if(stmt!=null & !stmt.isClosed())
                        stmt.close();
                   if(callableStatement!=null & !callableStatement.isClosed()){
                       callableStatement.close();
                   }
                   conn.rollback();
                    conn.close();
               }catch(SQLException ex2){
                   ex2.printStackTrace();
                    
               }
            e.printStackTrace();
        }

    }
}
