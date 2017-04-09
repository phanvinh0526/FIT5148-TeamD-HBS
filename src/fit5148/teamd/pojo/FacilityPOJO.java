/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

/**
 *
 * @author Varun
 */
public class FacilityPOJO {
    private int facility_Id;
    private String descr;
    
      public FacilityPOJO(int facility_Id, String descr) {
        this.facility_Id = facility_Id;
        this.descr = descr;
    }
    

    public int getFacility_Id() {
        return facility_Id;
    }

    public void setFacility_Id(int facility_Id) {
        this.facility_Id = facility_Id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

  
}
