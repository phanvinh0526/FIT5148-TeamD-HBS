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
    private String  contactPhone;
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

    //  Guest
    private Integer guest1;
    private Integer guest2;
    private Integer guest3;
    private Integer guest4;
    private Integer guest5;
    
    public Integer getBookId() {
        return bookId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getContactPhone() {
        return contactPhone;
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

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public Integer getGuest1() {
        return guest1;
    }

    public Integer getGuest2() {
        return guest2;
    }

    public Integer getGuest3() {
        return guest3;
    }

    public Integer getGuest4() {
        return guest4;
    }

    public Integer getGuest5() {
        return guest5;
    }

    public void setGuest1(Integer guest1) {
        this.guest1 = guest1;
    }

    public void setGuest2(Integer guest2) {
        this.guest2 = guest2;
    }

    public void setGuest3(Integer guest3) {
        this.guest3 = guest3;
    }

    public void setGuest4(Integer guest4) {
        this.guest4 = guest4;
    }

    public void setGuest5(Integer guest5) {
        this.guest5 = guest5;
    }
    
}
