/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attori;

import java.awt.*;
import utilities.*;
import static finestre.Finestra.*;

public class Mob extends Rectangle{ //la classe Mob estende la classe Rectangle ereditando metodi e attributi

    private Posizione posizione, lastPos;
    private int id, salute, guadagno, velocità, velocitaframe;
    private boolean primaVolta, uP, ingioco, nelrange, morto;
    private Image immagine;
    private boolean[][] pos;
    private boolean rallentato;
    private long tempoRallentato;
    private int debuffVelocità;

    public boolean isRallentato() {
        return rallentato;
    }

    public void setRallentato(boolean rallentato) {
        this.rallentato = rallentato;
    }

    private final int dimPiastrella;

    public boolean isNelrange() {
        return nelrange;
    }

    public void setNelrange(boolean nelrange) {
        this.nelrange = nelrange;
    }

    public void setIngioco(boolean ingioco) {
        this.ingioco = ingioco;
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
        panel.setMobUccisi(panel.getMobUccisi() + 1);

    }

    //costruttore della classe Mob
    public Mob(Posizione posizione, int velocità, int id, int salute, int guadagno, int dimPiastrella, Image immagine) {
        this.ingioco = false;

        this.id = id;
        this.immagine= immagine;

        this.dimPiastrella = dimPiastrella;

        this.posizione = posizione;

        this.velocità = velocità;
        this.salute = salute;
        this.guadagno = guadagno;
        y = this.posizione.getXPlace();
        x = this.posizione.getYPlace();
        nelrange = false;
        rallentato=false;
        velocitaframe = 0;
        tempoRallentato=0;
        debuffVelocità=0;

        lastPos = new Posizione(x, y);
        primaVolta = true;
        pos = new boolean[13][20];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 20; j++) {

                pos[i][j] = false;
            }
        }
    }

    public int getDebuffVelocità() {
        return debuffVelocità;
    }

    public void setDebuffVelocità(int debuffVelocità) {
        this.debuffVelocità = debuffVelocità;
    }

    public long getTempoRallentato() {
        return tempoRallentato;
    }

    public void setTempoRallentato(long tempoRallentato) {
        this.tempoRallentato = tempoRallentato;
    }

    // il metodo spawn modifica dimensione e posizie del mob dichiarando che il mob è in gioco grazie alla variabile ingioco

    public void spawn() {

        setBounds(x, y, 40, 40);

        ingioco = true;
        morto = false;
        nelrange = false;

    }

    // il metodo disegna si occupa di disegnare il mob grazie al metodo drawImage 
 
    public void disegna(Graphics g) {

        g.drawImage(immagine, x, y, dimPiastrella, dimPiastrella, null);
        g.setColor(Color.red);
        g.fillRect(x, y-2, salute/2, 4);
        

    }

    public Posizione getPosizione() {
        return posizione;
    }

    public boolean isIngioco() {
        return ingioco;
    }

    public boolean isMorto() {
        return morto;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVelocità() {
        return velocità;
    }

    public void setVelocità(int velocità) {
        this.velocità = velocità;
    }

    public int getSalute() {
        return salute;
    }

    public void setSalute(int salute) {
        this.salute = salute;
    }

    public int getGuadagno() {
        return guadagno;
    }

    public void setGuadagno(int guadagno) {
        this.guadagno = guadagno;
    }

    
    public void togliVita() {

        giocatore.setVita(giocatore.getVita() - 10);
        iPanel.update(giocatore);
        panel.setMobUccisi(panel.getMobUccisi() + 1);

    }

    // il metodo avanzamento gestisce gli spostamenti del mob all'interno della mappa interessata verificando ogni volta la posizione corrente e successiva
    
    public void avanzamento(int[][] mappa) {
        if (velocitaframe >= velocità) {
            if (mappa[y / 40 + 1][x / 40] == 3 || mappa[y / 40][x / 40 + 1] == 3) {
                togliVita();
                this.ingioco = false;
                this.morto = true;
            } else if (primaVolta) {

                tiMuovi(mappa);
                primaVolta = false;

            } else if (x % 40 == 0 && y % 40 == 0) {
                if (uP == true) {
                    pos[(lastPos.getY() / 40) + 1][(lastPos.getX() / 40)] = true;

                    tiMuovi(mappa);
                } else {
                    pos[(lastPos.getY() / 40)][(lastPos.getX() / 40)] = true;
                    tiMuovi(mappa);
                }
                
            } else if (x % 40 != 0 || y % 40 != 0) {
                tiMuovi(mappa);
            }
            velocitaframe = 0;

        } else {
            velocitaframe += 1;
        }
    }

    public void tiMuovi(int[][] mappa) {
        int xM, yM;
        xM = (int) y / 40;
        yM = (int) x / 40;

        if (xM != 0 && mappa[xM][yM] == 1 && pos[xM - 1][yM] == false) {

            uP = true;
            lastPos.setY(y);
            lastPos.setX(x);
            y -= 1;
        } else if (mappa[xM][yM + 1] == 1 && pos[xM][yM + 1] == false) {
            uP = false;
            lastPos.setY(y);
            lastPos.setX(x);

            x += 1;
        } else if (mappa[xM + 1][yM] == 1 && pos[xM + 1][yM] == false) {
            uP = false;
            lastPos.setY(y);
            lastPos.setX(x);
            y += 1;

        } else if (mappa[xM][yM - 1] == 1 && pos[xM][yM - 1] == false) {
            uP = false;
            lastPos.setY(y);
            lastPos.setX(x);
            x -= 1;
        }
    }

    public void morte() {
        if (getSalute() <= 0) {
            giocatore.setSoldi(giocatore.getSoldi() + 2);

            iPanel.update(giocatore);

            setIngioco(false);
            if (morto == false) {
                setMorto(true);
            }
        }
    }
}
