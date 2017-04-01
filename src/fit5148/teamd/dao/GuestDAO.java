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
    
    public GuestDAO() throws SQLException{
        conn = OracleDBConnectionUtil.getInstance().getConnectionB();
        guestPojo = new GuestPOJO();
        personPojo = new PersonalDetailsPOJO();
        
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
        gf.setCheckedIn(rs.getString("CHECKED_IN").toCharArray()[0]);
        gf.setHotelId(rs.getInt("HOTEL_ID"));
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
        Object data[][] = {{}};
        DefaultTableModel dtm = new DefaultTableModel();
        
        //  DB Statement
        listGF = new ArrayList<>();
        Statement sm = conn.createStatement();
        String sql = String.format("SELECT GUEST_ID, PREFERENCES,"
                + "PD_ID, CHECKED_IN, HOTEL_ID, TITLE, F_NAME, L_NAME, DOB, COUNTRY,"
                + "CITY, STREET, POSTCODE, PH_NO, EMAIL"
                + "FROM GUEST G, PERSONAL_DETAILS P WHERE G.PD_ID=P.PD_ID "
                + "AND P.F_NAME LIKE '%s'", keyword);
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
        ResultSet rs = sm.executeQuery(sql);
        rs.next();
        gf = matchingGuestFrame(rs);
        
        return gf;
    }

    public Integer createNewGuest(GuestFramePOJO gf) throws SQLException {
        Statement sm = conn.createStatement();
        String sql = String.format("INSERT INTO PERSONAL_DETAIL(TITLE, F_NAME, L_NAME,"
                + "COUNTRY,CITY,STREET,POSTCODE,PH_NO,EMAIL) VALUES("
                + "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s')", 
                gf.getTitle(),gf.getF_Name(),gf.getL_Name(),gf.getCountry(),gf.getCity(),
                gf.getStreet(),gf.getPostCode(),gf.getPh_no(),gf.getEmail());
        int n = sm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        if(n>0){
            ResultSet rs = sm.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }else
            return -1;
    }

    public boolean updateGuest(GuestFramePOJO guestFramePojo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteGuest(GuestFramePOJO guestFramePojo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<GuestFramePOJO> getListGF() {
        return listGF;
    }
 
    
}
