/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.BookingFramePOJO;
import fit5148.teamd.pojo.BookingPOJO;
import fit5148.teamd.pojo.RoomPOJO;
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
public class BookingDAO {
    private Connection conn = null;
    private BookingPOJO bookingPojo = null;
    private RoomPOJO roomPojo = null;
    private Object columnHeaders[] = {"ROOM ID","ROOM TYPE","PRICE", "ROOM NO", "MAX CAP","HOTEL_ID","AVAILABLE"};
    private ArrayList<BookingFramePOJO> bfPojo = null;
    private Statement sm = null;
    private DefaultTableModel tableModel = null;
    
    public BookingDAO(){
        try {
            conn = OracleDBConnectionUtil.getInstance().getConnectionB();
            bookingPojo = new BookingPOJO();
            roomPojo = new RoomPOJO();
            bfPojo = new ArrayList<>();
            tableModel = new DefaultTableModel();
            
        } catch (SQLException e) {
            System.out.println("Can't connect DB, Code: "+e.getMessage());
        }
    }

    public DefaultTableModel search(String navigator, String... keys) {
        Object data[][] = new Object[100][7];
        bfPojo.clear();
        
        try {
            sm = conn.createStatement();
        } catch (SQLException ex) {
            System.out.print("Can't catch the connection, error code: "+ex.getMessage());
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  Filters
        if("filterAvailable".equals(navigator)){
            try {
                String sql = "SELECT ROOM_ID, ROOM_TYPE, PRICE, DESCR, ROOM_NO, "
                        + "MAX_CAP, HOTEL_ID, AVAILABLE FROM ROOM WHERE AVAILABLE='Y' OR AVAILABLE='y'";
                System.out.println(sql);
                querySearch(sql, data);
                sm.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }else if("filterRoomType".equals(navigator)){
            try {
                String sql = String.format("SELECT ROOM_ID, ROOM_TYPE, PRICE, DESCR, ROOM_NO, "
                        + "MAX_CAP, HOTEL_ID, AVAILABLE FROM ROOM WHERE ROOM_TYPE='%s'",keys);
                System.out.println(sql);
                querySearch(sql, data);
                sm.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }else if("filterOccupancy".equals(navigator)){
            try {
                String sql = String.format("SELECT ROOM_ID, ROOM_TYPE, PRICE, DESCR, ROOM_NO, "
                        + "MAX_CAP, HOTEL_ID, AVAILABLE FROM ROOM WHERE MAX_CAP=%s",keys);
                System.out.println(sql);
                querySearch(sql, data);
                sm.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }else if("filterPrice".equals(navigator)){
            try {
                String sql = String.format("SELECT ROOM_ID, ROOM_TYPE, PRICE, DESCR, ROOM_NO, "
                        + "MAX_CAP, HOTEL_ID, AVAILABLE FROM ROOM WHERE PRICE %s %s",keys[0],keys[1]);
                System.out.println(sql);
                querySearch(sql, data);
                sm.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return tableModel;
    }
    
    private void querySearch(String sql, Object[][] data) throws SQLException{
        ResultSet rsr = sm.executeQuery(sql);
        for(int i=0; rsr.next(); i++){
            data[i][0] = rsr.getInt("ROOM_ID");
            data[i][1] = rsr.getString("ROOM_TYPE");
            data[i][2] = rsr.getInt("PRICE");
            data[i][3] = rsr.getString("ROOM_NO");
            data[i][4] = rsr.getInt("MAX_CAP");
            data[i][5] = rsr.getInt("HOTEL_ID");
            data[i][6] = rsr.getString("AVAILABLE");
            bfPojo.add(matchingBookingFrame(rsr));
            System.out.println(data[i][0]); // why just 5?
        }
        tableModel.setRowCount(0); // reset content
        tableModel.setDataVector(data, columnHeaders);
    }
    
    private BookingFramePOJO matchingBookingFrame(ResultSet rs) throws SQLException{
        BookingFramePOJO bfPojo = new BookingFramePOJO();
        bfPojo.setRoomId(rs.getInt("ROOM_ID"));
        bfPojo.setRoomType(rs.getString("ROOM_TYPE"));
        bfPojo.setRoomPrice(rs.getFloat("PRICE"));
        bfPojo.setRoomDesc(rs.getString("DESCR"));
        bfPojo.setRoomNo(rs.getString("ROOM_NO"));
        bfPojo.setRoomMaxCap(rs.getInt("MAX_CAP"));
        bfPojo.setHotelId(rs.getInt("HOTEL_ID"));
        bfPojo.setRoomAvailable(rs.getString("AVAILABLE"));
        return bfPojo;
    }

    
    
}
