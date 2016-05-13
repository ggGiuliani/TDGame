/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attori;

import static finestre.Finestra.panel;
import java.awt.*;


public class Proiettile extends Rectangle {

    private boolean colpito, sparato;
    private double velocita;
    private int velocitaproiettile, velocitaframe, attacco;
    private Mob bersaglio;
    private Torretta tor;

    public boolean isColpito() {
        return colpito;
    }


    public boolean isSparato() {
        return sparato;
    }

    public void setSparato(boolean sparato) {
        this.sparato = sparato;
    }

    public double getVelocita() {
        return velocita;
    }

    public void setVelocita(double velocita) {
        this.velocita = velocita;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Proiettile(Mob bersaglio, int x, int y, int attacco,Torretta t) {
        this.x = x + 20;
        this.y = y + 20;
        tor=t;
        this.attacco = attacco;
        this.bersaglio = bersaglio;
        velocitaproiettile = 2;
        spawn();

    }

    public void spawn() {

        setBounds(x, y, 10, 10);

        sparato = true;
        colpito = false;
    }

    public void calcolaDirezione() {

        double xPi = x;
        double yPi = y;
        double yPf = bersaglio.getY() + 20;
        double xPf = bersaglio.getX() + 20;
        if (velocitaframe >= (velocitaproiettile*panel.getTorrette().size())) {

            if (xPi == xPf && yPi == yPf && this.colpito == false) {

                this.colpito = true;
                tor.colpisci(bersaglio);
//              colpisci(tor);
                ///
            } else if (xPi > xPf && yPi < yPf) {
                x -= 1;
                y += 1;

            } else if (xPi > xPf && yPi > yPf) {
                x -= 1;
                y -= 1;

            } else if (xPi < xPf && yPi < yPf) {
                x += 1;
                y += 1;

            } else if (xPi < xPf && yPi > yPf) {    

                x += 1;
                y -= 1;

            } else if (xPi == xPf && yPi > yPf) {
                y--;
            } else if (xPi == xPf && yPi < yPf) {
                y++;
            } else if (yPi == yPf && xPi < xPf) {
                x++;
            } else if (yPi == yPf && xPi > xPf) {
                x--;
            }
            velocitaframe = 0;
        } else {
            velocitaframe += 1;
        }
    }

    public void disegna(Graphics g) {
        
        switch(tor.getTipo()){
            case 'a':{
        g.setColor(Color.red);
        g.fillOval(x, y, 8, 8);
        g.setColor(Color.GREEN);
        g.drawOval(x, y, 8, 8); break;}
        
            case 'b': {g.setColor(Color.blue);
        g.fillOval(x, y, 8, 8);
        g.setColor(Color.white);
        g.drawOval(x, y, 8, 8); break;}
        
        default:{
        g.setColor(Color.black);
        g.fillOval(x, y, 8, 8);
        g.setColor(Color.GREEN);
        g.drawOval(x, y, 8, 8); break;}
            
            
            }
        

    }
    
    
    public void morte() {
        if (bersaglio.getSalute() == 0) {
            bersaglio.setIngioco(false);
            bersaglio.setMorto(true);
        }
    }
}
