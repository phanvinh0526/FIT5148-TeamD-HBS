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
    private int id ;
    private String  type = null;
    private String  description =null;
    private float price ;
    private String  roomNo ;
    private Integer maxCap;
    private Integer hotelId ;
    private Integer facilityId ;
    private char available ;

    public RoomPOJO(){
        
    }
   
    public RoomPOJO(int id, float price, String roomNo, int maxCap, int hotelId, String type, String description) {

        this.price = price;
        this.roomNo = roomNo;
        this.maxCap = maxCap;
        this.hotelId = hotelId;
        this.id = id;
        this.type = type;
        this.description = description;
    }
//constructor for inserting data

    public RoomPOJO(float price, String type, String roomNo, Integer maxCap, Integer hotelId, String description) {
        this.price = price;
        this.roomNo = roomNo;
        this.maxCap = maxCap;
        this.hotelId = hotelId;
        this.type = type;
        this.description = description;

    }

    //constructor for fetching room
    public RoomPOJO(int id, String type, String description, int price, String roomNo, int maxCap, int hotelId) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.roomNo = roomNo;
        this.maxCap = maxCap;
        this.hotelId = hotelId;

    }

    public RoomPOJO(int id, String type, String description, int price, String roomNo, int maxCap, int hotelId, char available) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.roomNo = roomNo;
        this.maxCap = maxCap;
        this.hotelId = hotelId;

        this.available = available;
    }

    public RoomPOJO(Float crtRPrice, String crtRType, String crtRNo, int crtRMaxCap, int currentHotelId, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public int getId() {
        return id;
    }



    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public char getAvailable() {
        return available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public void setAvailable(char available) {
        this.available = available;
    }
    
}
