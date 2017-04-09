/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.HotelPOJO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VinhPQ
 */
public class HotelDAO {
    HotelPOJO   hotel = null;
    
    public HotelPOJO getProfile() throws SQLException{
        
        
        String      sql = "select * from hotel";
        Connection  conn= OracleDBConnectionUtil.getInstance().getConnectionA();
        Statement   sm  = conn.createStatement();
        ResultSet   rs  = sm.executeQuery(sql);
        while(rs.next()){
            hotel.setId(rs.getInt(1));
            hotel.setName(rs.getString(1));
            hotel.setType(rs.getString(2));
            hotel.setCountry(rs.getString(3));
            hotel.setCity(rs.getString(4));
        }
        rs.close();
        sm.close();
        return hotel;
    }

    public void updateHotelName(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
//        String      sql = "select * from hotel";
//        Connection  conn= OracleDBConnectionUtil.getInstance().getConnection();
//        Statement   sm  = conn.createStatement();
//        ResultSet   rs  = sm.executeQuery(sql);
//        while(rs.next()){
//            hotel.setId(rs.getInt(1));
//            hotel.setName(rs.getString(1));
//            hotel.setType(rs.getString(2));
//            hotel.setCountry(rs.getString(3));
//            hotel.setCity(rs.getString(4));
//        }
//        rs.close();
//        sm.close();
    }
    
}
