/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

import java.sql.Date;

/**
 *
 * @author Varun
 */
public class PersonalDetailsPOJO {
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

    public PersonalDetailsPOJO(Integer pd_id,String title, String f_Name, String email) {
        this.pd_id = pd_id;
        this.title = title;
        this.f_Name = f_Name;
        this.email = email;
    }

    public PersonalDetailsPOJO(){}
    
    public PersonalDetailsPOJO(Integer pd_id, String title, String f_Name, String l_Name, Date dob, String city, String country, String street, String postCode, Integer ph_no, String email) {
        this.pd_id = pd_id;
        this.title = title;
        this.f_Name = f_Name;
        this.l_Name = l_Name;
        this.dob = dob;
        this.city = city;
        this.country = country;
        this.street = street;
        this.postCode = postCode;
        this.ph_no = ph_no;
        this.email = email;
    }
    public Integer getPd_id() {
        return pd_id;
    }

    public void setPd_id(Integer pd_id) {
        this.pd_id = pd_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getF_Name() {
        return f_Name;
    }

    public void setF_Name(String f_Name) {
        this.f_Name = f_Name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Integer getPh_no() {
        return ph_no;
    }

    public void setPh_no(Integer ph_no) {
        this.ph_no = ph_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
