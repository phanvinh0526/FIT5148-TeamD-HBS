/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

import java.util.Date;

/**
 *
 * @author Varun
 */
public class PaymentPOJO {
    
    private int pmt_Id;
    private Date pmt_Date;
    private String pay_Method;
    private float pay_amt;
    private int book_Id;

    public PaymentPOJO(int pmt_Id, Date pmt_Date, String pay_Method, float pay_amt, int book_Id) {
        this.pmt_Id = pmt_Id;
        this.pmt_Date = pmt_Date;
        this.pay_Method = pay_Method;
        this.pay_amt = pay_amt;
        this.book_Id = book_Id;
    }

    public PaymentPOJO(Date pmt_Date,String pay_Method, float pay_amt, int book_Id) {
        this.pmt_Date = pmt_Date;
        this.pay_amt = pay_amt;
        this.book_Id = book_Id;
        this.pay_Method = pay_Method;
    }

    

    
    
    public int getPmt_Id() {
        return pmt_Id;
    }

    public void setPmt_Id(int pmt_Id) {
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

    public float getPay_amt() {
        return pay_amt;
    }

    public void setPay_amt(float pay_amt) {
        this.pay_amt = pay_amt;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }
    
    
}
