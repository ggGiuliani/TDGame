/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author User
 */
public class Posizione {

    private int x, y, xPlace, yPlace;

    public Posizione(int x, int y) {

        this.x = x;
        this.y = y;
        this.xPlace = x * 40;
        this.yPlace = y * 40;

    }

    public void setXPlace(int xPlace) {
        this.xPlace = xPlace;
    }

    public void setYPlace(int yPlace) {
        this.yPlace = yPlace;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.xPlace = x * 40;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.yPlace = y * 40;
    }

    public int getXPlace() {
        return xPlace;
    }

    public int getYPlace() {
        return yPlace;
    }
}
