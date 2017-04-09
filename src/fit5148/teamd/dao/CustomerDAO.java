/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.CustomerPOJO;
import fit5148.teamd.pojo.PersonalDetailsPOJO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Varun
 */
public class CustomerDAO {

    Connection conn;

    public CustomerDAO() throws SQLException {
        conn = OracleDBConnectionUtil.getInstance().getConnectionB();
    }

    public HashMap<CustomerPOJO, PersonalDetailsPOJO> searchCustomerByEmail(String eml) {
        HashMap<CustomerPOJO, PersonalDetailsPOJO> cust = new HashMap<CustomerPOJO, PersonalDetailsPOJO>();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from customer  natural join personal_details   where UPPER(email) = ? ");
            stmt.setString(1, eml.toUpperCase());

            ResultSet rs = stmt.executeQuery();
            boolean empty = true;
            while (rs.next()) {

                cust.put(new CustomerPOJO(rs.getInt("cust_id"), rs.getInt("mem_Credit"), rs.getString("mem_Tier"), rs.getInt("pd_id")), new PersonalDetailsPOJO(rs.getInt("pd_id"), rs.getString("title"), rs.getString("f_name"), rs.getString("l_name"), rs.getDate("DOB"), rs.getString("City"), rs.getString("Country"), rs.getString("Street"), rs.getString("Postcode"), rs.getInt("ph_no"), rs.getString("Email")));
                empty = false;
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

            return cust;
        }
        return cust;
    }

    public boolean updateCustomerDetails(HashMap<CustomerPOJO, PersonalDetailsPOJO> cust) {
        HashMap<CustomerPOJO, PersonalDetailsPOJO> cus = cust;
        CustomerPOJO custp = null;
        PersonalDetailsPOJO pd = null;
        boolean status = true;
        PreparedStatement sm = null;
        CallableStatement callableStatement = null;
        try {
            conn.setAutoCommit(false);
            conn.commit();

            for (Map.Entry<CustomerPOJO, PersonalDetailsPOJO> entry : cust.entrySet()) {
                custp = entry.getKey();
                pd = entry.getValue();
            }
            if (custp != null && pd != null) {

                sm = conn.prepareStatement("update Customer set mem_tier =?, mem_Credit=? where cust_id = ?");
                sm.setString(1, custp.getMem_Tier());
                sm.setInt(2, custp.getMem_Credit());
                sm.setInt(3, custp.getCustId());
                if (sm.executeUpdate() == 0) {
                    status = false;
                }
                sm.close();
                sm = conn.prepareStatement("update Personal_Details set title=? ,f_name=? ,l_name=? ,dob=?,country=?,city=?,street=?,postcode=?,ph_no=? where pd_id = ?");
                sm.setString(1, pd.getTitle());
                sm.setString(2, pd.getF_Name());
                sm.setString(3, pd.getL_Name());
                sm.setDate(4, pd.getDob() == null ? null : new java.sql.Date(pd.getDob().getTime()));
                sm.setString(5, (pd.getCountry() == null || pd.getCountry().compareTo("") == 0) ? null : pd.getCountry());
                sm.setString(6, (pd.getCity() == null || pd.getCity().compareTo("") == 0) ? null : pd.getCity());
                sm.setString(7, (pd.getStreet() == null || pd.getStreet().compareTo("") == 0) ? null : pd.getStreet());
                sm.setString(8, pd.getPostCode() == null || pd.getPostCode().compareTo("") == 0 ? null : pd.getPostCode());

                sm.setInt(9, (pd.getPh_no() == 0) ? 0 : pd.getPh_no());

                sm.setInt(10, pd.getPd_id());
                if (sm.executeUpdate() == 0) {
                    status = false;
                }

                String updateCustomerEmail = "{call  update_cust_email(?,?)}";
                callableStatement = conn.prepareCall(updateCustomerEmail);
                callableStatement.setString(1, pd.getEmail());
                callableStatement.setInt(2, pd.getPd_id());
                // execute stored procedure
                if (callableStatement.executeUpdate() == 0) {
                    status = false;
                }
                if (!status) {
                    conn.rollback();
                } else {
                    conn.commit();
                }
                callableStatement.close();
                sm.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            status = false;
            try {
                if (sm != null & !sm.isClosed()) {
                    sm.close();
                }
                if (callableStatement != null & !callableStatement.isClosed()) {
                    callableStatement.close();
                }
                conn.rollback();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }

            return status;
        }
        return status;

    }

    public HashMap<CustomerPOJO, PersonalDetailsPOJO> searchCustomerByMembership(String mem) {
        HashMap<CustomerPOJO, PersonalDetailsPOJO> cust = new HashMap<CustomerPOJO, PersonalDetailsPOJO>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("select * from customer  natural join personal_details   where mem_tier = ? ");
            stmt.setString(1, mem);

            rs = stmt.executeQuery();
            boolean empty = true;
            if (rs != null && stmt != null) {
                while (rs.next()) {
                    cust.put(new CustomerPOJO(rs.getInt("cust_id"), rs.getInt("mem_Credit"), rs.getString("mem_Tier"), rs.getInt("pd_id")), new PersonalDetailsPOJO(rs.getInt("pd_id"), rs.getString("title"), rs.getString("f_name"), rs.getString("l_name"), rs.getDate("DOB"), rs.getString("City"), rs.getString("Country"), rs.getString("Street"), rs.getString("Postcode"), rs.getInt("ph_no"), rs.getString("Email")));
                    empty = false;
                }

                rs.close();

                stmt.close();
            }
        } catch (SQLException ex) {
            try {
                rs.close();
                if (!stmt.isClosed()) {
                    stmt.close();
                }

            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex.printStackTrace();

            return cust;
        }
        return cust;
    }

    public boolean createNewCustomer(PersonalDetailsPOJO pd, int hId) {
        int pdId = 0;
        CustomerPOJO cust = null;
        PreparedStatement sm = null;
        boolean status = true;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            conn.commit();

            if (pd != null) {

                sm = conn.prepareStatement("insert into personal_details values (personal_details_seq.nextval,?,?,?,?,?,?,?,?,?,?)");

                sm.setString(1, pd.getTitle());
                sm.setString(2, pd.getF_Name());
                sm.setString(3, pd.getL_Name());
                sm.setDate(4, pd.getDob() == null ? null : new java.sql.Date(pd.getDob().getTime()));
                sm.setString(5, (pd.getCountry() == null || pd.getCountry().compareTo("") == 0) ? null : pd.getCountry());
                sm.setString(6, (pd.getCity() == null || pd.getCity().compareTo("") == 0) ? null : pd.getCity());
                sm.setString(7, (pd.getStreet() == null || pd.getStreet().compareTo("") == 0) ? null : pd.getStreet());
                sm.setString(8, pd.getPostCode() == null || pd.getPostCode().compareTo("") == 0 ? null : pd.getPostCode());
                sm.setInt(9, (pd.getPh_no() == 0) ? 0 : pd.getPh_no());
                sm.setString(10, pd.getEmail());

                if (sm.executeUpdate() == 0) {
                    return false;
                }
                sm.close();

                sm = conn.prepareStatement("select pd_id from personal_details where email = ?");
                sm.setString(1, pd.getEmail());
                rs = sm.executeQuery();
                while (rs.next()) {
                    pdId = rs.getInt("PD_ID");
                }
                rs.close();
                sm.close();
                if (pdId != 0) {
                    sm = conn.prepareStatement("insert into customer values (customer_seq.nextval,?,?,?)");
                    sm.setInt(1, 0);
                    sm.setString(2, "BLUE");
                    sm.setInt(3, pdId);

                    if (sm.executeUpdate() == 0) {
                        return false;
                    }
                } else {
                    return false;
                }
                if (!status) {
                    conn.rollback();
                } else {
                    conn.commit();
                }

                sm.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (sm != null && !sm.isClosed()) {
                    sm.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                conn.rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }

        }
        return status;
    }

    public boolean deleteCustomer(int pdId) {

        CustomerPOJO cust = null;
        PreparedStatement sm = null;
        boolean status = true;
        try {
            conn.setAutoCommit(false);
            conn.commit();

            if (pdId != 0) {

                sm = conn.prepareStatement("delete from personal_details where pd_id = ?");

                sm.setInt(1, pdId);

                if (sm.executeUpdate() == 0) {
                    status = false;
                }

                sm.close();

                if (!status) {
                    conn.rollback();
                } else {
                    conn.commit();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            status = false;
            try {
                if (!sm.isClosed()) {
                    sm.close();
                }
                conn.rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }

            return status;
        }
        return status;
    }

    public HashMap<CustomerPOJO, PersonalDetailsPOJO> searchAllCustomers(){
         HashMap<CustomerPOJO, PersonalDetailsPOJO> cust = new HashMap<CustomerPOJO, PersonalDetailsPOJO>();
          Statement stmt =null;
           ResultSet rs =null;
        try {

             stmt = conn.createStatement();
            

             rs = stmt.executeQuery("select * from customer  natural join personal_details  ");
            boolean empty = true;
            while (rs.next()) {

                cust.put(new CustomerPOJO(rs.getInt("cust_id"), rs.getInt("mem_Credit"), rs.getString("mem_Tier"), rs.getInt("pd_id")), new PersonalDetailsPOJO(rs.getInt("pd_id"), rs.getString("title"), rs.getString("f_name"), rs.getString("l_name"), rs.getDate("DOB"), rs.getString("City"), rs.getString("Country"), rs.getString("Street"), rs.getString("Postcode"), rs.getInt("ph_no"), rs.getString("Email")));
                empty = false;
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
             
            try {
                if (stmt!=null & !stmt.isClosed()) {
                    stmt.close();
                }if (rs!=null & !rs.isClosed()) {
                    rs.close();
                }
                conn.rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return cust;
        }
        return cust;
    }
}
