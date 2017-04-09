/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.CustomerPOJO;
import fit5148.teamd.pojo.PersonalDetailsPOJO;
import fit5148.teamd.pojo.RoomFramePOJO;
import fit5148.teamd.pojo.RoomPOJO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Varun
 */
public class RoomDAO {

    Connection conn;

    public RoomDAO() throws SQLException {
        conn = OracleDBConnectionUtil.getInstance().getConnectionB();
    }

    public ArrayList<RoomFramePOJO> getRoomsByFac(String facDescr, int hotelId) {
        ArrayList<RoomFramePOJO> rm = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> rmId = null;
        HashMap<Integer, String> facArr;
        ArrayList<RoomPOJO> roomArr;
        try {
            rm = new ArrayList<>();
            facArr = new HashMap<>();
            roomArr = new ArrayList<>();
            stmt = conn.prepareStatement("select room_id from room_to_facility where  facility_id = (select facility_id from room_facility where descr = ?) ");
            // stmt.setInt(1, hotelId);
            stmt.setString(1, facDescr);

            rs = stmt.executeQuery();
            rmId = new ArrayList<>();
            while (rs.next()) {

                rmId.add(new Integer(rs.getInt("room_id")));

            }

            rs.close();
            stmt.close();
            if (rmId.size() == 0) {
                return rm;
            }
            stmt = conn.prepareStatement("select * from room where room_id = ? and  hotel_id=?");
            for (int i = 0; i < rmId.size(); i++) {

                stmt.setInt(1, rmId.get(i));
                stmt.setInt(2, hotelId);
                rs = stmt.executeQuery();

                while (rs.next()) {

                    roomArr.add(new RoomPOJO(rs.getInt("room_id"), rs.getFloat("price"), rs.getString("room_no"), rs.getInt("max_cap"), rs.getInt("hotel_id"), rs.getString("room_type"), rs.getString("descr")));

                }

                rs.close();

            }
            stmt.close();
            //fetching facility info for each room
            String facCat = null;
            stmt = conn.prepareStatement("select * from room_facility where facility_id in (select facility_id from room_to_facility where room_id = ?) ");
            for (int i = 0; i < roomArr.size(); i++) {
                facCat = "";
                stmt.setInt(1, roomArr.get(i).getId());
                rs = stmt.executeQuery();

                while (rs.next()) {
                    if (facCat.compareTo("") == 0) {
                        facCat += rs.getString("descr");
                    } else {
                        facCat += "+" + rs.getString("descr");
                    }

                    //roomArr.add(new RoomPOJO(rs.getInt("room_id"),rs.getFloat("price"), rs.getString("room_no"),rs.getInt("max_cap"), rs.getInt("hotel_id"),rs.getString("room_type")));
                }
                //facArr.put(roomArr.get(i).getId(),facCat);
                rm.add(new RoomFramePOJO(roomArr.get(i).getId(), roomArr.get(i).getRoomNo(), roomArr.get(i).getPrice(), roomArr.get(i).getMaxCap(), facCat, roomArr.get(i).getType(), roomArr.get(i).getDescription()));
                rs.close();

            }
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

            return null;
        }
        return rm;

    }

    public ArrayList<RoomFramePOJO> getAllRooms(int hotelId) {
        ArrayList<RoomFramePOJO> rm = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> rmId = null;
        HashMap<Integer, String> facArr;
        ArrayList<RoomPOJO> roomArr;
        try {
            rm = new ArrayList<>();
            facArr = new HashMap<>();
            roomArr = new ArrayList<>();

            stmt = conn.prepareStatement("select * from room where hotel_id=? ");

            stmt.setInt(1, hotelId);
            rs = stmt.executeQuery();

            while (rs.next()) {

                roomArr.add(new RoomPOJO(rs.getInt("room_id"), rs.getFloat("price"), rs.getString("room_no"), rs.getInt("max_cap"), rs.getInt("hotel_id"), rs.getString("room_type"), rs.getString("descr")));

            }

            rs.close();
            stmt.close();
            if (roomArr.size() == 0) {
                return rm;
            }
            //fetching facility info for each room
            String facCat = null;
            stmt = conn.prepareStatement("select * from room_facility where facility_id in (select facility_id from room_to_facility where room_id = ?) ");
            for (int i = 0; i < roomArr.size(); i++) {
                facCat = "";
                stmt.setInt(1, roomArr.get(i).getId());
                rs = stmt.executeQuery();

                while (rs.next()) {
                    if (facCat.compareTo("") == 0) {
                        facCat += rs.getString("descr");
                    } else {
                        facCat += "+" + rs.getString("descr");
                    }

                    //roomArr.add(new RoomPOJO(rs.getInt("room_id"),rs.getFloat("price"), rs.getString("room_no"),rs.getInt("max_cap"), rs.getInt("hotel_id"),rs.getString("room_type")));
                }
                //facArr.put(roomArr.get(i).getId(),facCat);
                rm.add(new RoomFramePOJO(roomArr.get(i).getId(), roomArr.get(i).getRoomNo(), roomArr.get(i).getPrice(), roomArr.get(i).getMaxCap(), facCat, roomArr.get(i).getType(), roomArr.get(i).getDescription()));
                rs.close();

            }
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

            return null;
        }
        return rm;

    }

    public boolean updateRoom(RoomFramePOJO rm, int rmId, int hId, boolean isRoomNoChanged) {
        boolean result = true;
        String facArr[] = rm.getAllFacilities().split("\\+");
        ResultSet rs = null;
        boolean facArrPresent[] = new boolean[facArr.length];
        int facArrId[] = new int[facArr.length];
        PreparedStatement sm = null;

        try {
            conn.setAutoCommit(false);
            conn.commit();

            if (rmId != 0 & rm != null) {
                boolean room_exists = false;
                //Check if room exists in same hotel
                if (isRoomNoChanged) {
                    sm = conn.prepareStatement("select * from room where hotel_id=? and room_no=? ");

                    sm.setInt(1, hId);
                    sm.setString(2, rm.getRoomNo());
                    rs = sm.executeQuery();

                    while (rs.next()) {
                        room_exists = true;

                    }

                    rs.close();
                    sm.close();
                }
                //end
                if (room_exists) {
                    return false;
                } else {

                    sm = conn.prepareStatement("update Room set room_type =?, price=?,room_no=?,max_cap=?,descr=? where room_id = ?");
                    sm.setString(1, rm.getRoom_type());
                    sm.setFloat(2, rm.getPrice());
                    sm.setString(3, rm.getRoomNo());
                    sm.setInt(4, rm.getMax_cap());
                    sm.setString(5, rm.getDescription());
                    sm.setInt(6, rmId);
                    if (sm.executeUpdate() == 0) {
                        result = false;
                    }
                    sm.close();
                    if (!result) {
                        conn.rollback();
                        return result;
                    }
                    if (facArr.length > 0 && facArr[0].compareTo("") != 0) {

                        sm = conn.prepareStatement("select facility_id from room_facility where descr =?");
                        for (int i = 0; i < facArr.length; i++) {
                            sm.setString(1, facArr[i]);
                            rs = sm.executeQuery();
                            facArrId[i] = 0;
                            while (rs.next()) {

                                facArrId[i] = rs.getInt("facility_id");

                            }

                            rs.close();

                        }
                        sm.close();
                    }
                    if (facArr[0].compareTo("") != 0 && facArrId.length > 0) {

                        sm = conn.prepareStatement("select * from  room_to_facility where facility_id=? and room_id=?");
                        sm.setInt(2, rmId);
                        for (int i = 0; i < facArr.length; i++) {
                            sm.setInt(1, facArrId[i]);

                            rs = sm.executeQuery();
                            facArrPresent[i] = false;
                            while (rs.next()) {

                                facArrPresent[i] = true;

                            }

                            rs.close();

                        }
                        sm.close();

                        sm = conn.prepareStatement("insert into room_to_facility values(?,?)");
                        sm.setInt(1, rmId);
                        for (int i = 0; i < facArrPresent.length; i++) {
                            if (!facArrPresent[i]) {
                                //facArrId[i]

                                sm.setInt(2, facArrId[i]);

                                if (sm.executeUpdate() == 0) {
                                    result = false;
                                }

                                if (!result) {
                                    sm.close();
                                    conn.rollback();
                                    return result;
                                }

                            }

                        }
                        sm.close();
                    }
                }

                if (!result) {
                    conn.rollback();
                } else {
                    conn.commit();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
            try {
                if (sm != null & !sm.isClosed()) {
                    sm.close();
                }

                conn.rollback();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }

            return result;
        }
        return result;
    }

    public boolean createRoom(RoomPOJO rm, HashMap<Integer, String> fc) {

        boolean result = true;

        ResultSet rs = null;

        PreparedStatement sm = null;

        try {
            conn.setAutoCommit(false);
            conn.commit();

            if (rm != null) {
                //Check if room exists in same hotel

                sm = conn.prepareStatement("select * from room where hotel_id=? and room_no=? ");

                sm.setInt(1, rm.getHotelId());
                sm.setString(2, rm.getRoomNo());
                rs = sm.executeQuery();
                boolean room_exists = false;
                while (rs.next()) {
                    room_exists = true;

                }

                rs.close();
                sm.close();
                //end
                if (room_exists) {
                    return false;
                } else {

                    sm = conn.prepareStatement("insert into Room values (Room_seq.nextval,?,?,?,?,?,?,?)");
                    sm.setString(1, rm.getType());

                    sm.setFloat(2, rm.getPrice());
                    sm.setString(3, rm.getDescription());
                    sm.setString(4, rm.getRoomNo());
                    sm.setInt(5, rm.getMaxCap());
                    sm.setInt(6, rm.getHotelId());

                    sm.setString(7, String.valueOf('Y'));

                    if (sm.executeUpdate() == 0) {
                        result = false;
                    }
                    sm.close();

                    if (!result) {
                        conn.rollback();
                        return result;
                    }
                    //

                    //
                    if (fc.size() > 0) {

                        sm = conn.prepareStatement("insert into room_to_facility values((select room_id from room where hotel_id = ? and room_No = ?),?)");
                        for (Map.Entry<Integer, String> entry : fc.entrySet()) {

                            sm.setInt(1, rm.getHotelId());
                            sm.setString(2, rm.getRoomNo());
                            sm.setInt(3, entry.getKey());

                            if (sm.executeUpdate() == 0) {
                                result = false;
                            }

                            if (!result) {
                                sm.close();
                                conn.rollback();
                                return result;
                            }

                        }
                        sm.close();
                    }

                }

                if (!result) {
                    conn.rollback();
                } else {
                    conn.commit();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
            try {
                if (sm != null & !sm.isClosed()) {
                    sm.close();
                }

                conn.rollback();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }

            return result;
        }
        return result;

    }

    public boolean delRoom(int rId) {
        boolean result = true;

        PreparedStatement sm = null;

        try {
            conn.setAutoCommit(false);
            conn.commit();

            if (rId != 0) {

                sm = conn.prepareStatement("delete from room where room_id = ?");
                sm.setInt(1, rId);

                if (sm.executeUpdate() == 0) {
                    result = false;
                }
                sm.close();
                if (!result) {
                    conn.rollback();
                    return result;
                } else {
                    conn.commit();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
            try {
                if (sm != null & !sm.isClosed()) {
                    sm.close();
                }

                conn.rollback();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }

            return result;
        }
        return result;
    }

}
