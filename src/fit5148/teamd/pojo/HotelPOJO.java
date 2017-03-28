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
public class HotelPOJO {
    //  Class Entity HOTEL
    private Integer id;
    private String  name;
    private String  type;
    private String  country;
    private Integer constYr;
    private String  city;
    private String  address;
    private String  email;
    private Integer phNo;

    public HotelPOJO() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public Integer getConstYr() {
        return constYr;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPhNo() {
        return phNo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setConstYr(Integer constYr) {
        this.constYr = constYr;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhNo(Integer phNo) {
        this.phNo = phNo;
    }
    
    
}
