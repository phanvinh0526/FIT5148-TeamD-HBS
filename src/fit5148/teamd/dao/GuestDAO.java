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
import java.sql.SQLException;
import java.util.Properties;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author VinhPQ
 */
public class GuestDAO {

    private static UtilDateModel model;
    private static JDatePanelImpl datePanel;
    private Connection conn = null;
    private GuestPOJO guestPojo = null;
    private PersonalDetailsPOJO personPojo = null;
    
    public GuestDAO() throws SQLException{
        conn = OracleDBConnectionUtil.getInstance().getConnectionB();
        guestPojo = new GuestPOJO();
        personPojo = new PersonalDetailsPOJO();
        
    }
    
    public static JDatePickerImpl getDatePickerModel() {
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        
        datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        return datePicker;
    }

    public GuestFramePOJO searchByName(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public GuestFramePOJO searchById(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean createNewGuest(GuestFramePOJO collectFormData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateGuest(GuestFramePOJO guestFramePojo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteGuest(GuestFramePOJO guestFramePojo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
