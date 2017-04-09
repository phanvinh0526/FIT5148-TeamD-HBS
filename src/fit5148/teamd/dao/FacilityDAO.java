/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.FacilityPOJO;
import fit5148.teamd.pojo.MembershipPOJO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Varun
 */
public class FacilityDAO {

    Connection conn;

    public ArrayList<FacilityPOJO> getAllFacilities() {
        ArrayList<FacilityPOJO> facArr = new ArrayList<FacilityPOJO>();

        Statement sm = null;
        ResultSet rs = null;
        try {
            String sql = "select * from room_facility";
            conn = OracleDBConnectionUtil.getInstance().getConnectionB();
            sm = conn.createStatement();
            rs = sm.executeQuery(sql);

            while (rs.next()) {

                facArr.add(new FacilityPOJO(rs.getInt(1), rs.getString(2)));

            }

            rs.close();
            sm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                if (sm != null & !sm.isClosed()) {
                    sm.close();
                }
                if (rs != null & !rs.isClosed()) {
                    rs.close();
                }

            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
            return null;
        }
        return facArr;
    }

    public FacilityDAO() throws SQLException {
        conn = OracleDBConnectionUtil.getInstance().getConnectionB();
    }

}
