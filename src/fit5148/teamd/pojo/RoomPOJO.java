/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

/**
 *
 * @author VinhPQ
 */
public class RoomPOJO {
    private Integer id ;
    private String  type = null;
    private String  description =null;
    private Float price ;
    private String  roomNo ;
    private Integer maxCap;
    private Integer hotelId ;
    private Integer facilityId ;
    private char available ;

    public RoomPOJO(){}
   
    public RoomPOJO(Integer id, Float price, String roomNo, Integer maxCap, Integer hotelId, Integer facilityId, char available) {
        this.id = id;
        this.price = price;
        this.roomNo = roomNo;
        this.maxCap = maxCap;
        this.hotelId = hotelId;
        this.facilityId = facilityId;
        this.available = available;
    }

  
    public RoomPOJO(Integer id, String type, String description, Float price, String roomNo, Integer maxCap, Integer hotelId, Integer facilityId, char available) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.roomNo = roomNo;
        this.maxCap = maxCap;
        this.hotelId = hotelId;
        this.facilityId = facilityId;
        this.available = available;
    }
    
     public Integer getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public Integer getMaxCap() {
        return maxCap;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public char getAvailable() {
        return available;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setMaxCap(Integer maxCap) {
        this.maxCap = maxCap;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public void setAvailable(char available) {
        this.available = available;
    }
    
}
