/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.GuestFramePOJO;
import fit5148.teamd.pojo.GuestPOJO;
import fit5148.teamd.pojo.PersonalDetailsPOJO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author VinhPQ
 */
public class GuestDAO {

//    private static UtilDateModel model;
//    private static JDatePanelImpl datePanel;
    private Connection conn = null;
    private GuestPOJO guestPojo = null;
    private PersonalDetailsPOJO personPojo = null;
    private ArrayList<GuestFramePOJO> listGF = null;
    
    public GuestDAO(){
        try {
            conn = OracleDBConnectionUtil.getInstance().getConnectionB();
            guestPojo = new GuestPOJO();
            personPojo = new PersonalDetailsPOJO();
        } catch (SQLException ex) {
            System.out.println(("Can't connect to the database"));
            Logger.getLogger(GuestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//    public static JDatePickerImpl getDatePickerModel() {
//        model = new UtilDateModel();
//        Properties p = new Properties();
//        p.put("text.today","Today");
//        p.put("text.month","Month");
//        p.put("text.year","Year");
//        
//        datePanel = new JDatePanelImpl(model, p);
//        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//        return datePicker;
//    }

    private GuestFramePOJO matchingGuestFrame(ResultSet rs) throws SQLException{
        GuestFramePOJO gf = new GuestFramePOJO();
        gf.setGuestId(rs.getInt("GUEST_ID"));
        gf.setPreferences(rs.getString("PREFERENCES"));
        gf.setCheckedIn(rs.getString("CHECKED_IN"));
        gf.setHotelId(rs.getInt("HOTEL_ID"));
        gf.setPd_id(rs.getInt("PD_ID"));
        gf.setTitle(rs.getString("TITLE"));
        gf.setF_Name(rs.getString("F_NAME"));
        gf.setL_Name(rs.getString("L_NAME"));
//        gf.setDob(rs.getInt("DOB"));
        gf.setCountry(rs.getString("COUNTRY"));
        gf.setCity(rs.getString("CITY"));
        gf.setStreet(rs.getString("STREET"));
        gf.setPostCode(rs.getString("POSTCODE"));
        gf.setPh_no(rs.getInt("PH_NO"));
        gf.setEmail(rs.getString("EMAIL"));
        return gf;
    }
    
    public DefaultTableModel searchByName(String keyword) throws SQLException{
        //  Setup jTable
        Object columnHeaders[] = {"Guest ID","Personal ID","First Name","Email","Phone"};
        Object data[][] = new Object[10][5];
        DefaultTableModel dtm = new DefaultTableModel();
        
        //  DB Statement
        listGF = new ArrayList<>();
        Statement sm = conn.createStatement();
        String sql = "SELECT G.GUEST_ID, G.PREFERENCES,"
                + "P.PD_ID, G.CHECKED_IN, G.HOTEL_ID, P.TITLE, P.F_NAME, P.L_NAME, P.DOB, P.COUNTRY,"
                + "P.CITY, P.STREET, P.POSTCODE, P.PH_NO, P.EMAIL "
                + "FROM GUEST G, PERSONAL_DETAILS P WHERE G.PD_ID=P.PD_ID "
                + "AND P.F_NAME LIKE '%"+keyword+"%'";
        System.out.println(sql);
        ResultSet rs = sm.executeQuery(sql);
        for(int i=0; rs.next(); i++){
            data[i][0] = rs.getInt("GUEST_ID");
            data[i][1] = rs.getInt("PD_ID");
            data[i][2] = rs.getString("F_NAME");
            data[i][3] = rs.getString("EMAIL");
            data[i][4] = rs.getInt("PH_NO");
            listGF.add(matchingGuestFrame(rs));
        }
        dtm.setDataVector(data, columnHeaders);
        return dtm;
    }

    public GuestFramePOJO searchById(String keyword) throws SQLException{
        GuestFramePOJO gf = null;
        Statement sm = conn.createStatement();
        String sql = String.format("SELECT GUEST_ID, PREFERENCES,"
                + "PD_ID, CHECKED_IN, HOTEL_ID, TITLE, F_NAME, L_NAME, DOB, COUNTRY,"
                + "CITY, STREET, POSTCODE, PH_NO, EMAIL"
                + "FROM GUEST G, PERSONAL_DETAILS P WHERE G.PD_ID=P.PD_ID AND PD_ID=%s", keyword);
        System.out.println(sql);
        ResultSet rs = sm.executeQuery(sql);
        rs.next();
        gf = matchingGuestFrame(rs);
        sm.close();
        return gf;
    }

    public Integer createNewGuest(GuestFramePOJO gf) throws SQLException {
        Statement sm = conn.createStatement();
        //  Get Primary key from Sequence first
        String sql_0 = "select PERSONAL_DETAILS_SEQ.NEXTVAL from dual";
        Integer primaryKey = -1;
        ResultSet rs = sm.executeQuery(sql_0);
        if(rs.next())
            primaryKey = rs.getInt(1);
        
        //  Insert into Personal_Details
        String sql_1 = String.format("INSERT INTO PERSONAL_DETAILS(PD_ID, TITLE, F_NAME, L_NAME,"
                + "COUNTRY,CITY,STREET,POSTCODE,PH_NO,EMAIL) VALUES("
                + "%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, '%s')", 
                primaryKey, gf.getTitle(),gf.getF_Name(),gf.getL_Name(),gf.getCountry(),gf.getCity(),
                gf.getStreet(),gf.getPostCode(),gf.getPh_no(),gf.getEmail());
        System.out.println(sql_1);
        int n = sm.executeUpdate(sql_1, Statement.RETURN_GENERATED_KEYS);
        //  Insert into Guest
        String sql_2 = String.format("INSERT INTO GUEST(GUEST_ID, PREFERENCES, PD_ID, CHECKED_IN, HOTEL_ID) "
                + "VALUES(GUEST_SEQ.NEXTVAL, '%s', %d, '%s', %d)", 
                gf.getPreferences(), primaryKey, 'N', 1);
        System.out.println(sql_2);
        int m = sm.executeUpdate(sql_2, Statement.RETURN_GENERATED_KEYS);
        rs.close();;
        sm.close();
        if(n>0 && m>0) return primaryKey; else return -1;
    }

    public boolean updateGuest(GuestFramePOJO gf){
        try {
            System.out.println("get in update guest");
            Statement sm = conn.createStatement();
            String sql_1 = String.format("UPDATE PERSONAL_DETAILS P "
                    + "SET P.TITLE='%s', P.F_NAME='%s', P.L_NAME='%s', P.COUNTRY='%s', "
                    + "P.CITY='%s', P.STREET='%s', P.POSTCODE='%s', P.PH_NO=%d, "
                    + "P.EMAIL='%s' WHERE "
                    + "P.PD_ID=%s", gf.getTitle(),
                    gf.getF_Name(), gf.getL_Name(), gf.getCountry(), gf.getCity(), 
                    gf.getStreet(), gf.getPostCode(),gf.getPh_no(),gf.getEmail(),
                    gf.getPd_id());
            String sql_2 = String.format("UPDATE GUEST G SET G.PREFERENCES='%s', G.CHECKED_IN='%s' "
                    + "WHERE G.PD_ID='%s'", gf.getPreferences(), gf.getCheckedIn(), gf.getPd_id());
            System.out.println(sql_1);
            System.out.println(sql_2);
            int n = sm.executeUpdate(sql_1);
            int m = sm.executeUpdate(sql_2);
            sm.close();
            if(n>=0 && m>=0) return true;
            else 
                return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteGuest(GuestFramePOJO gf){
        try {
           Statement sm = conn.createStatement();
            String sql = "DELETE FROM GUEST WHERE GUEST_ID="+gf.getGuestId();
            System.out.println(sql);
            int n = sm.executeUpdate(sql);
            sm.close();
            if(n>=0){
                return true;
            }else{
                return false;
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<GuestFramePOJO> getListGF() {
        return listGF;
    }
 
    
}
