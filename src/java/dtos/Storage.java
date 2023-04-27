/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author SE130280
 */
public class Storage {

    private int stID;
    private int gameid;
    private String username;

    public Storage() {
    }

    public Storage(int stID, int gameid, String username) {
        
        this.stID = stID;
        this.gameid = gameid;
        this.username = username;

    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStID() {
        return stID;
    }

    public void setStID(int stID) {
        this.stID = stID;
    }

}
