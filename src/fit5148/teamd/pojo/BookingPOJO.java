/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

import java.util.Date;

/**
 *
 * @author Vinh Phan
 */
public class BookingPOJO {
    private Integer bookId;
    private Date checkIn;
    private Date checkOut;
    private String contactP;
    private String contactEml;
    private Float totalAmount;
    private String payStatus;
    private Integer customerId;
    private Date bookDate;
    private Integer hotelId;
    
    public BookingPOJO(){
        
    }
    
    public BookingPOJO(Integer bookId, Date checkIn, Date checkOut, String contactP, String contactEml, Float totalAmount, String payStatus, Integer customerId, Date bookDate, Integer hotelid){
        this.bookId=bookId;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
        this.contactP=contactP;
        this.contactEml=contactEml;
        this.totalAmount=totalAmount;
        this.payStatus=payStatus;
        this.customerId=customerId;
        this.bookDate=bookDate;
        this.hotelId=hotelid;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getContactP() {
        return contactP;
    }

    public String getContactEml() {
        return contactEml;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setContactP(String contactP) {
        this.contactP = contactP;
    }

    public void setContactEml(String contactEml) {
        this.contactEml = contactEml;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
    
    
}
