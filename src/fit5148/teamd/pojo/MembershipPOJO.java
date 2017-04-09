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
public class MembershipPOJO {
    private String mem_Tier;
    private float tier_Min_Cred;
    private float disc;
    private String other_Rew="";

    public MembershipPOJO(String mem_Tier, float tier_Min_Cred, float disc) {
        this.mem_Tier = mem_Tier;
        this.tier_Min_Cred = tier_Min_Cred;
        this.disc = disc;
    }

    public MembershipPOJO(String mem_Tier, float tier_Min_Cred, float disc, String other_Rew) {
        this.mem_Tier = mem_Tier;
        this.tier_Min_Cred = tier_Min_Cred;
        this.disc = disc;
        this.other_Rew = other_Rew;
    }

    
    
    public String getMem_Tier() {
        return mem_Tier;
    }

    public void setMem_Tier(String mem_Tier) {
        this.mem_Tier = mem_Tier;
    }

    public float getTier_Min_Cred() {
        return tier_Min_Cred;
    }

    public void setTier_Min_Cred(float tier_Min_Cred) {
        this.tier_Min_Cred = tier_Min_Cred;
    }

    public float getDisc() {
        return disc;
    }

    public void setDisc(float disc) {
        this.disc = disc;
    }

    public String getOther_Rew() {
        return other_Rew;
    }

    public void setOther_Rew(String other_Rew) {
        this.other_Rew = other_Rew;
    }
    
    
}
