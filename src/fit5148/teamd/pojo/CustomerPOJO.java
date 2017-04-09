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
    
    private int custId;
    private  int mem_Credit;
    private String mem_Tier;
    private int pd_Id;

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getMem_Credit() {
        return mem_Credit;
    }

    public CustomerPOJO(int custId, int mem_Credit, String mem_Tier, int pd_Id) {
        this.custId = custId;
        this.mem_Credit = mem_Credit;
        this.mem_Tier = mem_Tier;
        this.pd_Id = pd_Id;
        
    }

    public CustomerPOJO(int mem_Credit, String mem_Tier) {
        this.mem_Credit = mem_Credit;
        this.mem_Tier = mem_Tier;
    }

    public void setMem_Credit(int mem_Credit) {
        this.mem_Credit = mem_Credit;
    }

    public String getMem_Tier() {
        return mem_Tier;
    }

    public void setMem_Tier(String mem_Tier) {
        this.mem_Tier = mem_Tier;
    }

    public int getPd_Id() {
        return pd_Id;
    }

    public void setPd_Id(int pd_Id) {
        this.pd_Id = pd_Id;
    }

  

    
    
}
