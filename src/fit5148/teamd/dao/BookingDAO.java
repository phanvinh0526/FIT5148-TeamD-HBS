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
    private ArrayList<BookingFramePOJO> bookingFramePojo = null;
    private Statement sm = null;
    private DefaultTableModel tableModel = null;
    
    public BookingDAO(){
        try {
            conn = OracleDBConnectionUtil.getInstance().getConnectionB();
            bookingPojo = new BookingPOJO();
            roomPojo = new RoomPOJO();
            bookingFramePojo = new ArrayList<>();
            tableModel = new DefaultTableModel();
            
        } catch (SQLException e) {
            System.out.println("Can't connect DB, Code: "+e.getMessage());
        }
    }

    public DefaultTableModel search(String navigator, String... keys) {
        Object data[][] = new Object[100][7];
        bookingFramePojo.clear();
        
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
            bookingFramePojo.add(matchingBookingFrame(rsr));
        }
        tableModel.setRowCount(0); // reset content
        tableModel.setDataVector(data, columnHeaders);
    }
    
    private BookingFramePOJO matchingBookingFrame(ResultSet rs) throws SQLException{
        BookingFramePOJO bookingFramePojo = new BookingFramePOJO();
        bookingFramePojo.setRoomId(rs.getInt("ROOM_ID"));
        bookingFramePojo.setRoomType(rs.getString("ROOM_TYPE"));
        bookingFramePojo.setRoomPrice(rs.getFloat("PRICE"));
        bookingFramePojo.setRoomDesc(rs.getString("DESCR"));
        bookingFramePojo.setRoomNo(rs.getString("ROOM_NO"));
        bookingFramePojo.setRoomMaxCap(rs.getInt("MAX_CAP"));
        bookingFramePojo.setHotelId(rs.getInt("HOTEL_ID"));
        bookingFramePojo.setRoomAvailable(rs.getString("AVAILABLE"));
        return bookingFramePojo;
    }

    public ArrayList<BookingFramePOJO> getBookingFramePojo() {
        return bookingFramePojo;
    }

    public Integer insert(BookingFramePOJO bf) {
        try {
            Statement sm = conn.createStatement();
            //  Get Primary key from Sequence first
            String sql_0 = "SELECT BOOKING_SEQ.NEXTVAL FROM dual";
            Integer primaryKey = -1;
            ResultSet rs = sm.executeQuery(sql_0);
            if(rs.next())
                primaryKey = rs.getInt(1);
            
            //  Insert into Personal_Details
            String sql_1 = String.format("INSERT INTO BOOKING(BOOK_ID, CHECK_IN, CHECK_OUT, CONTACT_P "
                    + "CONTACT_EML, TOT_AMT, PAY_STATUS, CUST_ID, BOOK_DATE, HOTEL_ID) VALUES("
                    + "%d, '%s', '%s', '%s', '%s', '%f', '%s', '%d', %s, '%d')",
                    primaryKey, bf.getCheckIn(),bf.getCheckOut(),bf.getContactPerson(),
                    bf.getContactEmail(),bf.getTotAmt(),bf.getTotAmt(),bf.getCustId(),bf.getBookDate(),
                    bf.getHotelId());
            System.out.println(sql_1);
            int n = sm.executeUpdate(sql_1, Statement.RETURN_GENERATED_KEYS);
            //  Insert into Guest
            String sql_2 = String.format("INSERT INTO SUBBOOKING(ROOM_ID, BOOK_ID) "
                    + "VALUES(%d, %d)", bf.getRoomId(), primaryKey);
            System.out.println(sql_2);
            int m = sm.executeUpdate(sql_2, Statement.RETURN_GENERATED_KEYS);
            rs.close();
            sm.close();
            if(n>0 && m>0) return primaryKey; else return -1;
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public Integer update(BookingFramePOJO bf) {
        try {
            Statement sm = conn.createStatement();
            int n, m = -1;
            String sql_1 = String.format("UPDATE BOOKING B "
                    + "SET B.CHECK_IN='%s', B.CHECK_OUT='%s', B.CONTACT_P='%s', B_CONTACT_EML='%s', "
                    + "B.TOT_AMT='%d', B.PAY_STATUS='%s', B.CUST_ID='%d', B.BOOK_DATE=%d, "
                    + "B.HOTEL_ID='%d' WHERE "
                    + "B.BOOK_ID=%d", bf.getCheckIn(),bf.getCheckOut(),bf.getContactPerson(),bf.getContactEmail(),
                    bf.getTotAmt(),bf.getPayStatus(),bf.getCustId(),bf.getBookDate(),bf.getHotelId(),bf.getBookId());
            System.out.println(sql_1);
            n = sm.executeUpdate(sql_1);
            if(n>=0){
                String sql_2 = String.format("UPDATE SUBBOOKNG S SET S.ROOM_ID=%d "
                        + "WHERE S.BOOK_ID=%d", bf.getRoomId(), bf.getBookId());

                System.out.println(sql_2);
                m = sm.executeUpdate(sql_2);
            }
            else return -1;
            sm.close();
            if(n>=0 && m>=0) return 1; else  return -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
}
