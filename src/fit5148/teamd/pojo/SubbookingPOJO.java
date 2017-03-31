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
public class SubbookingPOJO {
    
    private Integer roomId;
    private Integer bookId;
    
    public SubbookingPOJO(){
        
    }
    
    public SubbookingPOJO(Integer roomId, Integer bookId){
        this.roomId=roomId;
        this.bookId=bookId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    
}
