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
public class ShoppingCartItem {
    private Game game;

    public ShoppingCartItem(Game game) {
        this.game = game;
    }

    public ShoppingCartItem() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
}
