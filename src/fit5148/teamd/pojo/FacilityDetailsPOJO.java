/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

/**
 *
 * @author Vinh Phan
 */
public class FacilityDetailsPOJO {
    
    private Integer roomId;
    private Integer facilityId;
    
    public FacilityDetailsPOJO(){
    
    }
    
    public FacilityDetailsPOJO(Integer roomId, Integer facId){
        this.roomId=roomId;
        this.facilityId=facId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }
    
}
