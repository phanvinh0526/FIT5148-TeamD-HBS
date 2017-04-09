/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

/**
 *
 * @author Varun
 */
public class RoomFramePOJO {
    
    private int room_id;
    private String roomNo;
    private String  description =null;
    private float price;
    private int max_cap;
    private String allFacilities;
    private String room_type;

    public RoomFramePOJO(int room_id, String roomNo, float price, int max_cap, String allFacilities, String room_type,String description) {
        this.room_id = room_id;
        this.roomNo = roomNo;
        this.price = price;
        this.max_cap = max_cap;
        this.allFacilities = allFacilities;
        this.room_type = room_type;
         this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMax_cap() {
        return max_cap;
    }

    public void setMax_cap(int max_cap) {
        this.max_cap = max_cap;
    }

    public String getAllFacilities() {
        return allFacilities;
    }

    public void setAllFacilities(String allFacilities) {
        this.allFacilities = allFacilities;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
    
    
}
