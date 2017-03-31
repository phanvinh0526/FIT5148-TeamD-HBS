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
public class PaymentPOJO {
    
    private Integer pmt_Id;
    private Date pmt_Date;
    private String pay_Method;
    private Float pay_amt;
    private Integer book_Id;

    public PaymentPOJO(Integer pmt_Id, Date pmt_Date, String pay_Method, Float pay_amt, Integer book_Id) {
        this.pmt_Id = pmt_Id;
        this.pmt_Date = pmt_Date;
        this.pay_Method = pay_Method;
        this.pay_amt = pay_amt;
        this.book_Id = book_Id;
    }

    
    
    public Integer getPmt_Id() {
        return pmt_Id;
    }

    public void setPmt_Id(Integer pmt_Id) {
        this.pmt_Id = pmt_Id;
    }

    public Date getPmt_Date() {
        return pmt_Date;
    }

    public void setPmt_Date(Date pmt_Date) {
        this.pmt_Date = pmt_Date;
    }

    public String getPay_Method() {
        return pay_Method;
    }

    public void setPay_Method(String pay_Method) {
        this.pay_Method = pay_Method;
    }

    public Float getPay_amt() {
        return pay_amt;
    }

    public void setPay_amt(Float pay_amt) {
        this.pay_amt = pay_amt;
    }

    public Integer getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(Integer book_Id) {
        this.book_Id = book_Id;
    }
    
    
}
