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
    private int id  ;
    private String  name;
    private String  type ;
    private String  country ;
    private int constYr = 0;
    private String  city ;
    private String  address;
    private String  email ;
    private int phNo ;

    public HotelPOJO(int id, String type, String country, String city, String address, String email, int phNo) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phNo = phNo;
    }

   
    public HotelPOJO(int id, String name, String type, String country, int constYr, String city, String address, String email, int phNo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
        this.constYr = constYr;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phNo = phNo;
    }

    
    public int getId() {
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

    public int getConstYr() {
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

    public int getPhNo() {
        return phNo;
    }

    public void setId(int id) {
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

    public void setConstYr(int constYr) {
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

    public void setPhNo(int phNo) {
        this.phNo = phNo;
    }
    
    
}
