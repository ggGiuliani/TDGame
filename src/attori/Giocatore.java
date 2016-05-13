/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attori;

/**
 *
 * @author User
 */
public class Giocatore {

    private int vita, soldi;

    public Giocatore(int vita, int soldi) {

        this.vita = vita;
        this.soldi = soldi;

    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getSoldi() {
        return soldi;
    }

    public void setSoldi(int soldi) {
        this.soldi = soldi;
    }

    public void aggiungiSoldi(int soldi) {
        this.soldi += soldi;
    }
}
