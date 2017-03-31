/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.pojo;

import java.util.logging.Logger;

/**
 *
 * @author Vinh Phan
 */
public class SubbookingGuestsPOJO {
    private static final Logger LOG = Logger.getLogger(SubbookingGuestsPOJO.class.getName());
    
    private Integer roomId;
    private Integer bookId;
    private Integer guestId;
    
    public SubbookingGuestsPOJO(){
        
    }
    
    public SubbookingGuestsPOJO(Integer roomId, Integer bookId, Integer guestId){
        this.roomId=roomId;
        this.bookId=bookId;
        this.guestId=guestId;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }
    
}
