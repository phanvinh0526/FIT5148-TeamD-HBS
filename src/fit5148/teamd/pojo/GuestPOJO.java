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
public class GuestPOJO {
    
    private Integer guestId;
    private String preferences;
    private Integer pd_id;
    private char checkedIn;
    private Integer hotelId;
    
    public GuestPOJO(){
        
    }

    public Integer getGuestId() {
        return guestId;
    }

    public String getPreferences() {
        return preferences;
    }

    public Integer getPd_id() {
        return pd_id;
    }

    public char getCheckedIn() {
        return checkedIn;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public void setPd_id(Integer pd_id) {
        this.pd_id = pd_id;
    }

    public void setCheckedIn(char checkedIn) {
        this.checkedIn = checkedIn;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
    
    
}
