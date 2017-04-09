/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

import java.util.Date;

/**
 *
 * @author Vinh Phan
 */
public class CustomerFramePOJO {
    //  Customer
    private Integer custId;
    private Integer memCredit;
    private String memTier;
    
    //  Personal Details
    private Integer perId;
    private String  perTitle;
    private String  perFName;
    private String  perLName;
    private Date    perDoB;
    private String  perCountry;
    private String  perCity;
    private String  perStreet;
    private String  perPostcode;
    private String  perEmail;
    private Integer perPhone;
    
    public Integer getPerId() {
        return perId;
    }

    public String getPerTitle() {
        return perTitle;
    }

    public String getPerFName() {
        return perFName;
    }

    public String getPerLName() {
        return perLName;
    }

    public Date getPerDoB() {
        return perDoB;
    }

    public String getPerCountry() {
        return perCountry;
    }

    public String getPerCity() {
        return perCity;
    }

    public String getPerStreet() {
        return perStreet;
    }

    public String getPerPostcode() {
        return perPostcode;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public Integer getPerPhone() {
        return perPhone;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public void setPerTitle(String perTitle) {
        this.perTitle = perTitle;
    }

    public void setPerFName(String perFName) {
        this.perFName = perFName;
    }

    public void setPerLName(String perLName) {
        this.perLName = perLName;
    }

    public void setPerDoB(Date perDoB) {
        this.perDoB = perDoB;
    }

    public void setPerCountry(String perCountry) {
        this.perCountry = perCountry;
    }

    public void setPerCity(String perCity) {
        this.perCity = perCity;
    }

    public void setPerStreet(String perStreet) {
        this.perStreet = perStreet;
    }

    public void setPerPostcode(String perPostcode) {
        this.perPostcode = perPostcode;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public void setPerPhone(Integer perPhone) {
        this.perPhone = perPhone;
    }

    public Integer getCustId() {
        return custId;
    }

    public Integer getMemCredit() {
        return memCredit;
    }

    public String getMemTier() {
        return memTier;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public void setMemCredit(Integer memCredit) {
        this.memCredit = memCredit;
    }

    public void setMemTier(String memTier) {
        this.memTier = memTier;
    }
    
}
