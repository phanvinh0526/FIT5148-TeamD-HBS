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
public class MembershipPOJO {
    
    private String memTier;
    private Integer tierMinCred;
    private Integer disc;
    private String otherRew;
    
    public MembershipPOJO(){
        
    }
    
    public MembershipPOJO(String mem,Integer tierMin, Integer disc, String other){
        this.memTier=mem;
        this.disc=disc;
        this.tierMinCred=tierMin;
        this.otherRew=other;
    }

    public String getMemTier() {
        return memTier;
    }

    public Integer getTierMinCred() {
        return tierMinCred;
    }

    public Integer getDisc() {
        return disc;
    }

    public String getOtherRew() {
        return otherRew;
    }

    public void setMemTier(String memTier) {
        this.memTier = memTier;
    }

    public void setTierMinCred(Integer tierMinCred) {
        this.tierMinCred = tierMinCred;
    }

    public void setDisc(Integer disc) {
        this.disc = disc;
    }

    public void setOtherRew(String otherRew) {
        this.otherRew = otherRew;
    }
    
    
}
