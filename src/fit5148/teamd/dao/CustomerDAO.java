/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.CustomerFramePOJO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinh Phan
 */
public class CustomerDAO {
    private Connection conn = null;
//    private CustomerPOJO customerPojo = null;
//    private PersonalDetailsPOJO personPojo = null;
    private ArrayList<CustomerFramePOJO> listCF = null;
    
    public CustomerDAO(){
        try {
            this.conn = OracleDBConnectionUtil.getInstance().getConnectionB();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<CustomerFramePOJO> getSearchResult() {
        return listCF;
    }

    public DefaultTableModel searchCustomer(String key) {
        //  Setup jTable
        Object columnHeaders[] = {"Personal ID","First Name","Last Name", "Email", "Phone"};
        Object data[][] = new Object[20][5];
        DefaultTableModel dtm = new DefaultTableModel();
        
        //  DB Statement
        listCF = new ArrayList<>();
        Statement sm = null;
        try {
            sm = conn.createStatement();

            String sql = "SELECT PD_ID, TITLE, "
                    + "F_NAME, L_NAME, COUNTRY, PH_NO, EMAIL "
                    + "FROM PERSONAL_DETAILS P WHERE P.F_NAME LIKE '%"+key+"%'";
            System.out.println(sql);
            ResultSet rs = sm.executeQuery(sql);
            for(int i=0; rs.next(); i++){
                data[i][0] = rs.getInt("PD_ID");
                data[i][1] = rs.getString("F_NAME");
                data[i][2] = rs.getString("L_NAME");
                data[i][3] = rs.getString("EMAIL");
                data[i][4] = rs.getInt("PH_NO");
                listCF.add(matchingCustomerFrame(rs));
            }
        } catch (SQLException ex) {
            System.out.print("Can't catch the connection, error code: "+ex.getMessage());
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        dtm.setDataVector(data, columnHeaders);
        return dtm;
    }

    private CustomerFramePOJO matchingCustomerFrame(ResultSet rs) throws SQLException{
        CustomerFramePOJO cf = new CustomerFramePOJO();
        cf.setPerId(rs.getInt("PD_ID"));
        cf.setPerFName(rs.getString("F_NAME"));
        cf.setPerLName(rs.getString("L_NAME"));
        cf.setPerEmail(rs.getString("EMAIL"));
        cf.setPerPhone(rs.getInt("PH_NO"));
        return cf;
    }
    
    
}
