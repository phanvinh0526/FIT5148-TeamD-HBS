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
public class BookingFramePOJO {
    //  Booking entity
    private Integer bookId;
    private Date    checkIn;
    private Date    checkOut;
    private String  contactPerson;
    private String  contactEmail;
    private Float   totAmt;
    private String  payStatus;
    private Integer custId;
    private Date    bookDate;
    private Integer hotelId;
    
    //  Room entity
    private Integer roomId;
    private String  roomType;
    private Float   roomPrice;
    private String  roomDesc;
    private String  roomNo;
    private Integer roomMaxCap;
    private String  roomAvailable;

    
    
    public Integer getBookId() {
        return bookId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Float getTotAmt() {
        return totAmt;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public Integer getCustId() {
        return custId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public Float getRoomPrice() {
        return roomPrice;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public Integer getRoomMaxCap() {
        return roomMaxCap;
    }

    public String getRoomAvailable() {
        return roomAvailable;
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

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setTotAmt(Float totAmt) {
        this.totAmt = totAmt;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomPrice(Float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setRoomMaxCap(Integer roomMaxCap) {
        this.roomMaxCap = roomMaxCap;
    }

    public void setRoomAvailable(String roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    
    
}
