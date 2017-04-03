/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import fit5148.teamd.pojo.PaymentPOJO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Varun
 */
public class PaymentDAO {
    Connection conn;
    public PaymentDAO()  throws SQLException{
        conn= OracleDBConnectionUtil.getInstance().getConnectionB();
    }
    
//    public PaymentPOJO[] getUnpaidPayments(){
//    
//    
//    }
//    
//    public boolean updatePayment( PaymentPOJO pmt){
//        
//        
//    }
//
//    public boolean deletePayment(PaymentPOJO pmt){
//    
//    }
//    
//    public PaymentPOJO[] getAllPayments(){
//    
//    }
//    
    public ArrayList<ArrayList<String>> getPaymentByCustEml(String eml,int hId){
        ArrayList<ArrayList<String>> paymentDt=  new ArrayList<>(); 
        ArrayList<String> temp;
        String fName="",lName="",flName="";
        int custId,pdId=0;
        ArrayList<Integer> pmtId;
        ArrayList<Integer> bookId = new ArrayList<>() ;
        ArrayList<String> checkinDate,checkoutDate,pmt_method,pmt_date,pmt_amt,rooms;
        checkinDate = new ArrayList<>() ;
        pmt_method = new ArrayList<>() ;
        pmt_date = new ArrayList<>() ;
        pmt_amt = new ArrayList<>() ;
        rooms = new ArrayList<>() ;
         pmtId = new ArrayList<>() ;
        checkoutDate = new ArrayList<>() ;
        ResultSet   rs =null;
        PreparedStatement sm = null;
        String email="";
         Format f = new SimpleDateFormat("MM/dd/YYYY");
         boolean resultFound =true;
        //Storage sequence Pmt_ID,Customer_fullname,Cust_email,Pay_date,pay_mthd,Pay_amt,rooms,Checkin,Checout date

        //neeed CUSTOMER, PERSONAL DETAIL, INFO , SUBBOOKING ROOM NOs , BOOKING CHECKIN CHECKOUT DATE , PAYMENT INFO , payment_id
     
    try{
            //String      sql = "select * from customer c  join personal_details p on c.pd_id = p.PD_ID where p.email = "+eml;
            temp=new ArrayList<String>();
            sm=conn.prepareStatement("select pd_id,f_name,L_name,email from personal_details where UPPER(email) = ? ");  
            sm.setString(1,eml.toUpperCase());//1 specifies the first parameter in the query  
            
  
            //int i=stmt.executeUpdate();  
            
              rs  = sm.executeQuery();
          
            while(rs.next()){
                // CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id, int hotelId)
                // public PersonalDetailsPOJO(int pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, int ph_no, String email) {
                fName=rs.getString("f_name");
                lName=rs.getString("l_name");
                flName=fName+" "+lName;
                pdId=rs.getInt("pd_id");
                email= rs.getString("email");
                               
            }
            
            rs.close();
            sm.close();
            if(pdId==0){
                //resultFound=false;
                return paymentDt;
            }
            sm=conn.prepareStatement("select book_id ,check_in,check_out from booking where check_in > sysdate and pay_status = 'PAID' and cust_id = (select cust_id from customer where   pd_id = ?) ");  
            sm.setInt(1,pdId);//1 specifies the first parameter in the query  
            
            
            
            
               rs  = sm.executeQuery();
           
                
                while(rs.next()){
                 bookId.add(rs.getInt("book_id"));
                checkinDate.add(f.format(rs.getDate("check_in")));
                checkoutDate.add(f.format(rs.getDate("check_out")));
         
                 }
            
            rs.close();
            sm.close();
            if(bookId.size()==0){
               
               // resultFound=false;
             return paymentDt;
            }
            
             sm=conn.prepareStatement("select pmt_id,pmt_date,pay_method,pay_amt from payment where book_id = ?");  
            
             
             for(int i=0;i<bookId.size();i++){
                    sm.setInt(1,bookId.get(i));

                      rs  = sm.executeQuery();

                       while(rs.next()){
                       // CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id, int hotelId)
                       // public PersonalDetailsPOJO(int pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, int ph_no, String email) {
                       pmtId.add(new Integer(rs.getInt("pmt_id")));
                       pmt_date.add(f.format(rs.getDate("pmt_date")));
                       pmt_method.add(rs.getString("pay_method"));
                       pmt_amt.add(rs.getString("pay_amt"));

                   }

                rs.close();
             }
            
            sm.close();
             if(pmtId.size()==0){
   
                //resultFound=false;
                 return paymentDt;
             }
            sm=conn.prepareStatement("select room_id from subbooking where book_id =  ?");  
            
             String concatRooms ;
             for(int i=0;i<bookId.size();i++){
                        concatRooms ="";
                     sm.setInt(1,bookId.get(i));

                      rs  = sm.executeQuery();
                       
                       while(rs.next()){
                       // CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id, int hotelId)
                       // public PersonalDetailsPOJO(int pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, int ph_no, String email) {
                     if(concatRooms.compareTo("")==0)
                         concatRooms+=rs.getInt("room_id");
                     else
                       concatRooms+=", "+ rs.getInt("room_id");

                   }
                         rooms.add(concatRooms);
                rs.close();
             }
            sm.close();
            //Storage sequence Pmt_ID,Customer_fullname,Cust_email,Pay_date,pay_mthd,Pay_amt,rooms,Checkin,Checout date
            for(int i=0;i<bookId.size(); i++){
                temp=new ArrayList<>();
                temp.add(String.valueOf(pmtId.get(i)));
                temp.add(fName);
                temp.add(email);
                temp.add(pmt_date.get(i));
                temp.add(pmt_method.get(i));
                temp.add(pmt_amt.get(i));
                temp.add(rooms.get(i));
                temp.add(checkinDate.get(i));
                temp.add(checkoutDate.get(i));
                paymentDt.add(temp);
            }
            
    }
        catch(SQLException ex){
            try {
                ex.printStackTrace();
                if(rs!=null && !rs.isClosed())
                rs.close();
                if(sm!=null && !sm.isClosed())
                sm.close();
                //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
                
            } catch (SQLException ex1) {
                Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
            return null;
        }
    return paymentDt;
        
        
        
    }
    
    public boolean deletePayment(int pmtId,float pmtAmt){
        
        
        PreparedStatement sm=null;
        boolean status=true;
        try{
                conn.setAutoCommit(false);
                conn.commit();
               //String      sql = "select * from customer c  join personal_details p on c.pd_id = p.PD_ID where p.email = "+eml;
               //where cust_id in (select cust_id from booking where book_id =(select book_id from payment where pmt_id = 2));
               if(pmtId!=0){
                             if(status){
                            sm=conn.prepareStatement("update customer set mem_credit = mem_credit - ? where cust_id in (select cust_id from booking where book_id =(select book_id from payment where pmt_id = ?))");
                            sm.setInt(1,Math.round(pmtAmt));
                               sm.setInt(2,pmtId);
                             if(sm.executeUpdate()==0)
                                status=false;
                                sm.close();
                        
                        }
                        
                         if(status){
                            sm=conn.prepareStatement("update booking set pay_status = 'UNPAID' where book_id = (select book_id from payment where pmt_id = ?)");
                            
                            sm.setInt(1,pmtId);
                             if(sm.executeUpdate()==0)
                                status=false;
                            sm.close();
                        
                        }
                         
                         sm=conn.prepareStatement("delete from payment where pmt_id = ?");
                       
                       
                          sm.setInt(1,pmtId);
                        
                        if(sm.executeUpdate()==0)
                            status=false;
                        
                        sm.close();
                        
                        
                   
                         
                         
                        if(!status)
                        {
                            conn.rollback();
                        }
                        else
                            conn.commit();

                      
                }
       }
           catch(SQLException ex){
               ex.printStackTrace();
               status=false;
               try{
                   if(!sm.isClosed())
                        sm.close();
                   conn.rollback();
               }catch(Exception ex2){
                   ex2.printStackTrace();
               }
                       
               
               
             //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
               return status;
           }
       return status;
    }
    
    public ArrayList<ArrayList<String>> getBookingByCustEml(String eml,int hId){
        ArrayList<ArrayList<String>> bookingDt= new ArrayList<>(); 
        ArrayList<String> temp;
        String fName="",lName="",flName="";
        int custId=0,pdId=0;
        ArrayList<Integer> pmtId;
        ArrayList<Integer> bookId = new ArrayList<>() ;
        ArrayList<String> checkinDate,checkoutDate,pmt_method,pmt_date,bk_amt,rooms;
        checkinDate = new ArrayList<>() ;
        pmt_method = new ArrayList<>() ;
        pmt_date = new ArrayList<>() ;
        bk_amt = new ArrayList<>() ;
        rooms = new ArrayList<>() ;
         pmtId = new ArrayList<>() ;
        checkoutDate = new ArrayList<>() ;
        ResultSet   rs ;
        PreparedStatement sm;
         Format f = new SimpleDateFormat("MM/dd/YYYY");
        //Storage sequence Pmt_ID,Customer_fullname,Cust_email,Pay_date,pay_mthd,Pay_amt,rooms,Checkin,Checout date

        //neeed CUSTOMER, PERSONAL DETAIL, INFO , SUBBOOKING ROOM NOs , BOOKING CHECKIN CHECKOUT DATE , PAYMENT INFO , payment_id
     
    try{
            //String      sql = "select * from customer c  join personal_details p on c.pd_id = p.PD_ID where p.email = "+eml;
            temp=new ArrayList<String>();
            sm=conn.prepareStatement("select pd_id,f_name,L_name from personal_details where UPPER(email)= ? ");  
            sm.setString(1,eml.toUpperCase());//1 specifies the first parameter in the query  
            
  
            //int i=stmt.executeUpdate();  
            
              rs  = sm.executeQuery();
            boolean empty = true;
            while(rs.next()){
                // CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id, int hotelId)
                // public PersonalDetailsPOJO(int pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, int ph_no, String email) {
                fName=rs.getString("f_name");
                lName=rs.getString("l_name");
                flName=fName+" "+lName;
                pdId=rs.getInt("pd_id");
                               
            }
            
            rs.close();
            sm.close();
            sm=conn.prepareStatement("select book_id ,check_in,check_out,cust_id,tot_amt from booking where check_in > sysdate and pay_status = 'UNPAID' and cust_id = (select cust_id from customer where   pd_id = ?) ");  
            sm.setInt(1,pdId);//1 specifies the first parameter in the query  
            
            
            //int i=stmt.executeUpdate();  
            
               rs  = sm.executeQuery();
           
                
                while(rs.next()){
                // CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id, int hotelId)
                // public PersonalDetailsPOJO(int pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, int ph_no, String email) {
                bookId.add(rs.getInt("book_id"));
                checkinDate.add(f.format(rs.getDate("check_in")));
                checkoutDate.add(f.format(rs.getDate("check_out")));
                custId=rs.getInt("cust_id");
                bk_amt.add(String.valueOf(rs.getFloat("tot_amt")));
         
            }
            
            rs.close();
            sm.close();
      
            //Storage sequence Pmt_ID,Customer_fullname,Cust_email,Pay_date,pay_mthd,Pay_amt,rooms,Checkin,Checout date
            for(int i=0;i<bookId.size(); i++){
                temp=new ArrayList<>();
                temp.add(bookId.get(i).toString());
                temp.add(fName);
                temp.add(String.valueOf(custId));  
                temp.add(checkinDate.get(i));
                temp.add(checkoutDate.get(i));
                temp.add(bk_amt.get(i));
                bookingDt.add(temp);
            }
            
    }
        catch(SQLException ex){
            ex.printStackTrace();
          //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
            return null;
        }
    return bookingDt;
        
        
        
    }
    
    public ArrayList<ArrayList<String>> getBookingByUnpaidStatus(int hId){
        ArrayList<ArrayList<String>> bookingDt= new ArrayList<>(); 
        ArrayList<String> temp;
        String fName="",lName="";
        int pdId=0;
        ArrayList<Integer> custId;
        ArrayList<Integer> bookId = new ArrayList<>() ;
        ArrayList<String> checkinDate,checkoutDate,flName,bk_amt;
        checkinDate = new ArrayList<>() ;
       bk_amt = new ArrayList<>() ;
        flName = new ArrayList<>() ;
        
         custId = new ArrayList<>() ;
        checkoutDate = new ArrayList<>() ;
        ResultSet   rs =null;
        PreparedStatement sm = null;
         Format f = new SimpleDateFormat("MM/dd/YYYY");
        //Storage sequence Pmt_ID,Customer_fullname,Cust_email,Pay_date,pay_mthd,Pay_amt,rooms,Checkin,Checout date

        //neeed CUSTOMER, PERSONAL DETAIL, INFO , SUBBOOKING ROOM NOs , BOOKING CHECKIN CHECKOUT DATE , PAYMENT INFO , payment_id
     
    try{
            //String      sql = "select * from customer c  join personal_details p on c.pd_id = p.PD_ID where p.email = "+eml;
            temp=new ArrayList<String>();
              sm=conn.prepareStatement("select book_id ,check_in,check_out,cust_id,tot_amt  from booking where check_in > sysdate and pay_status = 'UNPAID' and hotel_id= ?  ");  
            sm.setInt(1,hId);
            //1 specifies the first parameter in the query  
            
            
            //int i=stmt.executeUpdate();  
            
               rs  = sm.executeQuery();
           
                
                while(rs.next()){
                 bookId.add(rs.getInt("book_id"));
                checkinDate.add(f.format(rs.getDate("check_in")));
                checkoutDate.add(f.format(rs.getDate("check_out")));
                custId.add(rs.getInt("cust_id"));
                 bk_amt.add(String.valueOf(rs.getFloat("tot_amt")));
         
            }
            
            rs.close();
            sm.close();
            if(custId.size()==0){
                return bookingDt;
            }
            
            
            sm=conn.prepareStatement("select pd_id,f_name,L_name from personal_details where pd_id = (select pd_id from customer where cust_id= ?) ");  
            for(int i=0;i<custId.size(); i++){
                    sm.setInt(1,custId.get(i));//1 specifies the first parameter in the query  


                    //int i=stmt.executeUpdate();  

                      rs  = sm.executeQuery();
                    
                    while(rs.next()){
                        // CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id, int hotelId)
                        // public PersonalDetailsPOJO(int pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, int ph_no, String email) {
                        fName=rs.getString("f_name");
                        lName=rs.getString("l_name");
                        flName.add(fName+" "+lName);
                      

                    }

                    rs.close();
            }
              if(flName.size()==0){
                return bookingDt;
            }
            sm.close();
          
      
            //Storage sequence Pmt_ID,Customer_fullname,Cust_email,Pay_date,pay_mthd,Pay_amt,rooms,Checkin,Checout date
            for(int i=0;i<bookId.size(); i++){
                temp=new ArrayList<>();
                temp.add(bookId.get(i).toString());
                temp.add(fName);
                temp.add(String.valueOf(custId.get(i)));  
                temp.add(checkinDate.get(i));
                temp.add(checkoutDate.get(i));
                temp.add(bk_amt.get(i));
                
                bookingDt.add(temp);
            }
            
    }
        catch(SQLException ex){
             try {
                ex.printStackTrace();
                if(rs!=null && !rs.isClosed())
                rs.close();
                if(sm!=null && !sm.isClosed())
                sm.close();
                //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
                
            } catch (SQLException ex1) {
                Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
            ex.printStackTrace();
          //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
            return null;
        }
    return bookingDt;
        
        
        
    }
    
     public boolean addPayment(PaymentPOJO pm){
          
        PreparedStatement sm=null;
        boolean status=true;
        try{
                conn.setAutoCommit(false);
                conn.commit();
               //String      sql = "select * from customer c  join personal_details p on c.pd_id = p.PD_ID where p.email = "+eml;
               //where cust_id in (select cust_id from booking where book_id =(select book_id from payment where pmt_id = 2));
               if(pm!=null){
                        
                         sm=conn.prepareStatement("insert into payment values(PAYMENT_SEQ.NEXTVAL,?,?,?,?)");
                       
                       
                       sm.setDate(1,new java.sql.Date(pm.getPmt_Date().getTime()));
                       sm.setString(2,pm.getPay_Method());
                       sm.setFloat(3,pm.getPay_amt());
                        sm.setInt(4,pm.getBook_Id());
                        if(sm.executeUpdate()==0)
                            status=false;
                        
                        sm.close();
                        
                        
                        if(status){
                            sm=conn.prepareStatement("update customer set mem_credit = mem_credit + ? where cust_id in (select cust_id from booking where book_id = ?)");
                            sm.setInt(1,Math.round(pm.getPay_amt()));
                               sm.setInt(2,pm.getBook_Id());
                             if(sm.executeUpdate()==0)
                                status=false;
                                sm.close();
                        
                        }
                        
                         if(status){
                            sm=conn.prepareStatement("update booking set pay_status = 'PAID' where book_id =  ?");
                            
                            sm.setInt(1,pm.getBook_Id());
                             if(sm.executeUpdate()==0)
                                status=false;
                            sm.close();
                        
                        }
                         
                         
                        if(!status)
                        {
                            conn.rollback();
                        }
                        else
                            conn.commit();

                      
                }
       }
           catch(SQLException ex){
               status=false;
               try{
                   if(!sm.isClosed())
                        sm.close();
                   conn.rollback();
               }catch(Exception ex2){
                   ex2.printStackTrace();
               }
                       
               
               ex.printStackTrace();
             //  System.out.println("fit5148.teamd.dao.MembershipDAO.getAllMembership() : "+ex.);
               return status;
           }
       return status;
     }
}
