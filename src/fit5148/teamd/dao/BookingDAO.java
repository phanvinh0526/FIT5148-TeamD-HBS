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
import java.sql.Date;
import java.sql.PreparedStatement;
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
        bookingFramePojo = new ArrayList<>();
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
        BookingFramePOJO bf = new BookingFramePOJO();
        bf.setRoomId(rs.getInt("ROOM_ID"));
        bf.setRoomType(rs.getString("ROOM_TYPE"));
        bf.setRoomPrice(rs.getFloat("PRICE"));
        bf.setRoomDesc(rs.getString("DESCR"));
        bf.setRoomNo(rs.getString("ROOM_NO"));
        bf.setRoomMaxCap(rs.getInt("MAX_CAP"));
        bf.setHotelId(rs.getInt("HOTEL_ID"));
        bf.setRoomAvailable(rs.getString("AVAILABLE"));
        return bf;
    }

    public ArrayList<BookingFramePOJO> getBookingFramePojo() {
        return bookingFramePojo;
    }

//    public Integer insert(BookingFramePOJO bf) {
//        try {
//            PreparedStatement ps = null;
//            //  Get Primary key from Sequence first
//            String sql_0 = "SELECT BOOKING_SEQ.NEXTVAL FROM dual";
//            ps = conn.prepareStatement(sql_0);
//            Integer primaryKey = -1;
//            ResultSet rs = ps.executeQuery(sql_0);
//            if(rs.next())
//                primaryKey = rs.getInt(1);
//            rs.close();
//            ps.close();
//            
//            //  Insert into Personal_Details
//            String sql_1 = "INSERT INTO BOOKING(BOOK_ID, CHECK_IN, CHECK_OUT, CONTACT_P, "
//                    + "CONTACT_EML, TOT_AMT, PAY_STATUS, CUST_ID, BOOK_DATE, HOTEL_ID) VALUES(?,?,?,?,?,?,?,?,?,?)";   
//            ps = conn.prepareStatement(sql_1);
//            ps.setInt(1, primaryKey);
//            ps.setDate(2, bf.getCheckIn()==null?null: new Date(bf.getCheckIn().getTime())); // Must convert to sql.date from util.date
//            ps.setDate(3, bf.getCheckOut()==null?null:new Date(bf.getCheckOut().getTime()));
//            ps.setString(4, bf.getContactPhone());
//            ps.setString(5, bf.getContactEmail());
//            ps.setFloat(6, bf.getTotAmt());
//            ps.setString(7, bf.getPayStatus());
//            ps.setInt(8, bf.getCustId());
//            ps.setDate(9, bf.getBookDate()==null?null:new Date(bf.getBookDate().getTime()));
//            ps.setInt(10, bf.getHotelId());
//            
//            int n = ps.executeUpdate(); 
//            if(n==0){
//                ps.close();
//                return -1;
//            }
//            ps.close();
////          Insert into Guest
//            String sql_2 = "INSERT INTO SUBBOOKING(ROOM_ID, BOOK_ID) VALUES(?,?)";
//            System.out.println(sql_2);
//            ps = conn.prepareStatement(sql_2);
//            ps.setInt(1, bf.getRoomId());
//            ps.setInt(2, bf.getBookId());
//            
//            if(ps.executeUpdate(sql_2)!=0){
//                ps.close();
//                return primaryKey;
//            } else{
//                ps.close();
//                return -1;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return -1;
//        }
//    }

    public Integer insert(BookingFramePOJO bf) {
        try {
            Statement sm = conn.createStatement();
            //  Get Primary key from Sequence first
            String sql_0 = "SELECT BOOKING_SEQ.NEXTVAL FROM dual";
            
            Integer primaryKey = -1;
            ResultSet rs = sm.executeQuery(sql_0);
            if(rs.next())
                primaryKey = rs.getInt(1);
            rs.close();
            sm.close();
            
            //  Insert into Personal_Details
            sm = conn.createStatement();
            String sql_1 = String.format("INSERT INTO BOOKING(BOOK_ID, CHECK_IN, CHECK_OUT, CONTACT_P, "
                    + "CONTACT_EML, TOT_AMT, PAY_STATUS, CUST_ID, BOOK_DATE, HOTEL_ID) "
                    + "VALUES(%d,TO_DATE('%s', 'YYYY-MM-DD'),TO_DATE('%s', 'YYYY-MM-DD'),"
                    + "'%s','%s',%f,'%s',%d,TO_DATE('%s', 'YYYY-MM-DD'),%d)",
                    primaryKey,bf.getCheckIn()==null?null: new Date(bf.getCheckIn().getTime()),
                    bf.getCheckOut()==null?null:new Date(bf.getCheckOut().getTime()),
                    bf.getContactPhone(),
                    bf.getContactEmail(),
                    bf.getTotAmt(),
                    bf.getPayStatus(),
                    bf.getCustId(),
                    bf.getBookDate()==null?null:new Date(bf.getBookDate().getTime()),
                    bf.getHotelId());
            System.out.println(sql_1);
            int n = sm.executeUpdate(sql_1); 
            if(n==0){
                sm.close();
                conn.rollback();
                return -1;
            }
            sm.close();
//            conn.commit();
//          Insert into Guest
            sm = conn.createStatement();
            String sql_2 = String.format("INSERT INTO SUBBOOKING(ROOM_ID, BOOK_ID) VALUES(%d,%d)",
                    bf.getRoomId(),primaryKey);
            System.out.println(sql_2);
            
            if(sm.executeUpdate(sql_2)>=0){
                sm.close();
//                conn.commit();
                return primaryKey;
            } else{
                sm.close();
                conn.rollback();
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
                    + "B.TOT_AMT=%f, B.PAY_STATUS='%s', B.CUST_ID=%d, B.BOOK_DATE='%s', "
                    + "B.HOTEL_ID=%d WHERE "
                    + "B.BOOK_ID=%d", bf.getCheckIn().getTime(),bf.getCheckOut().getTime(),
                    bf.getContactPhone(),bf.getContactEmail(),
                    bf.getTotAmt(),bf.getPayStatus(),bf.getCustId(),
                    bf.getBookDate().getTime(),bf.getHotelId(),bf.getBookId());
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
