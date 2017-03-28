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
    private Integer id;
    private String  type;
    private String  description;
    private Integer price;
    private String  roomNo;
    private Integer maxCap;
    private Integer hotelId;
    private Integer facilityId;
    private Boolean available;

    public RoomPOJO() {
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

    public Integer getPrice() {
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

    public Boolean getAvailable() {
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

    public void setPrice(Integer price) {
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

    public void setAvailable(Boolean available) {
        this.available = available;
    }
    
}
