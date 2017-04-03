/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

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
public class MembershipDAO {
    
    public   ArrayList<MembershipPOJO>  getAllMembership(){
        ArrayList<MembershipPOJO> memArr=  new ArrayList<MembershipPOJO>();
      
        
        try{
            String      sql = "select * from membership";
            Connection  conn= OracleDBConnectionUtil.getInstance().getConnectionB();
            Statement   sm  = conn.createStatement();
            ResultSet   rs  = sm.executeQuery(sql);

            while(rs.next()){

                memArr.add(new MembershipPOJO(rs.getString(1),rs.getFloat(2),rs.getFloat(3), rs.getString(4)));

            }
            rs.close();
            sm.close();
    }
        catch(SQLException ex){
            ex.printStackTrace();
          //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
            return memArr;
        }
        return memArr;
    }
    
    
    
}
