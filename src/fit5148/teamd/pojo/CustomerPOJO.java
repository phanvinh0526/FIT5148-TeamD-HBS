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
public class CustomerPOJO {
    
    private Integer custId;
    private Integer mem_Credit;
    private String mem_Tier;
    private Integer pd_Id;
    private Integer hotelId;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getMem_Credit() {
        return mem_Credit;
    }

    public CustomerPOJO(Integer custId, Integer mem_Credit, String mem_Tier, Integer pd_Id, Integer hotelId) {
        this.custId = custId;
        this.mem_Credit = mem_Credit;
        this.mem_Tier = mem_Tier;
        this.pd_Id = pd_Id;
        this.hotelId = hotelId;
    }

    public void setMem_Credit(Integer mem_Credit) {
        this.mem_Credit = mem_Credit;
    }

    public String getMem_Tier() {
        return mem_Tier;
    }

    public void setMem_Tier(String mem_Tier) {
        this.mem_Tier = mem_Tier;
    }

    public Integer getPd_Id() {
        return pd_Id;
    }

    public void setPd_Id(Integer pd_Id) {
        this.pd_Id = pd_Id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
    
    
    
}
