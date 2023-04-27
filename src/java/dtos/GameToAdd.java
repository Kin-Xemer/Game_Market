package dtos;

import java.io.Serializable;

public class GameToAdd {
    private String gameName;
    private double price;
    private String categoryID;
    private String description;
    private String pubID;
    private String devID;
    private String releaseDay;
    private int rating;
    private String imgURL;
    private String username;
    private String linkdown;

    public GameToAdd() {
    }

    public GameToAdd(String gameName, double price, String categoryID, String description, String pubID, String devID, String releaseDay, int rating, String imgURL, String username, String linkdown) {
        this.gameName = gameName;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.pubID = pubID;
        this.devID = devID;
        this.releaseDay = releaseDay;
        this.rating = rating;
        this.imgURL = imgURL;
        this.username = username;
        this.linkdown = linkdown;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubID() {
        return pubID;
    }

    public void setPubID(String pubID) {
        this.pubID = pubID;
    }

    public String getDevID() {
        return devID;
    }

    public void setDevID(String devID) {
        this.devID = devID;
    }

    public String getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(String releaseDay) {
        this.releaseDay = releaseDay;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLinkdown() {
        return linkdown;
    }

    public void setLinkdown(String linkdown) {
        this.linkdown = linkdown;
    }
    
    
}