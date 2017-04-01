/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

import java.sql.Date;

/**
 *
 * @author VinhPQ
 */
public class GuestFramePOJO {
    //  Personal Detail
    private Integer pd_id;
    private String title;
    private String f_Name;
    private String l_Name;
    private Date dob;
    private String city;
    private String country;
    private String street;
    private String postCode;
    private Integer ph_no;
    private String email;
    //  Guest
    private Integer guestId;
    private String preferences;
    private char checkedIn;
    private Integer hotelId;
    //  Frame variables
    private String selection;
    private String keyword;
    
    public GuestFramePOJO(){}

    public Integer getPd_id() {
        return pd_id;
    }

    public String getTitle() {
        return title;
    }

    public String getF_Name() {
        return f_Name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public Date getDob() {
        return dob;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public Integer getPh_no() {
        return ph_no;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public String getPreferences() {
        return preferences;
    }

    public char getCheckedIn() {
        return checkedIn;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public String getSelection() {
        return selection;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setPd_id(Integer pd_id) {
        this.pd_id = pd_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setF_Name(String f_Name) {
        this.f_Name = f_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setPh_no(Integer ph_no) {
        this.ph_no = ph_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public void setCheckedIn(char checkedIn) {
        this.checkedIn = checkedIn;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    
}
