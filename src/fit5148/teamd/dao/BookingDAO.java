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
    private Object columnHeaders[] = {"RoomID","RoomType","Occupancy", "Price", "Status"};
    private Object data[][] = {{}};
    private DefaultTableModel tableModel = null;
    private ArrayList<BookingFramePOJO> bfPojo = null;
    private Statement sm = null;
    
    public BookingDAO(){
        try {
            conn = OracleDBConnectionUtil.getInstance().getConnectionB();
            bookingPojo = new BookingPOJO();
            roomPojo = new RoomPOJO();
            tableModel = new DefaultTableModel();
            bfPojo = new ArrayList<>();
            data = new Object[20][8];
            
        } catch (SQLException e) {
            System.out.println("Can't connect DB, Code: "+e.getMessage());
        }
    }

    public DefaultTableModel search(String operator, String... keys) {
        bfPojo.clear();
        try {
            sm = conn.createStatement();
        } catch (SQLException ex) {
            System.out.print("Can't catch the connection, error code: "+ex.getMessage());
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  Filters
        if(operator == "filterAvailable"){
            try {
                String sql = "SELECT ROOM_ID, ROOM_TYPE, PRICE, DESC, ROOM_NO, "
                        + "MAX_CAP, HOTEL_ID, AVAILABLE FROM ROOM WHERE AVAILABLE='Y'";
                ResultSet rs = sm.executeQuery(sql);
                for(int i=0; rs.next(); i++){
                    data[i][0] = rs.getInt("ROOM_ID");
                    data[i][1] = rs.getString("ROOM_TYPE");
                    data[i][2] = rs.getInt("PRICE");
                    data[i][3] = rs.getString("DESC");
                    data[i][4] = rs.getString("ROOM_NO");
                    data[i][5] = rs.getInt("MAX_CAP");
                    data[i][6] = rs.getInt("HOTEL_ID");
                    data[i][7] = rs.getString("AVAILABLE");
                    bfPojo.add(matchingBookingFrame(rs));
                }
                tableModel.setDataVector(data, columnHeaders);
                sm.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }else if(operator == ""){
            
        }
        
        return tableModel;
    }

    private BookingFramePOJO matchingBookingFrame(ResultSet rs) throws SQLException{
        BookingFramePOJO bfPojo = new BookingFramePOJO();
        bfPojo.setRoomId(rs.getInt("ROOM_ID"));
        bfPojo.setRoomType(rs.getString("ROOM_TYPE"));
        bfPojo.setRoomPrice(rs.getFloat("PRICE"));
        bfPojo.setRoomDesc(rs.getString("DESC"));
        bfPojo.setRoomNo(rs.getString("ROOM_NO"));
        bfPojo.setRoomMaxCap(rs.getInt("MAX_CAP"));
        bfPojo.setHotelId(rs.getInt("HOTEL_ID"));
        bfPojo.setRoomAvailable(rs.getString("AVAILABLE"));
        return bfPojo;
    }

    
    
}
