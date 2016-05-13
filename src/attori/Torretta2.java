/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attori;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import static finestre.Finestra.*;
import pannelli.Pannello;

public class Torretta2 extends Torretta {
 

    
    public Torretta2(int danno, int x, int y, ArrayList<Proiettile> proiettili) {
        super();
        costoUpgrade=costoAcquisto+5;
        velocitàAttacco = 4000;
        attacco = danno;
        this.proiettili = proiettili;
        this.x = x * 40;
        this.y = y * 40 - 40;
        range = new Ellipse2D.Double();
        range.setFrame(this.x - 40, this.y - 40, 119, 119);
        temposparo = 600;
        finestrasparo = 0;
        costoAcquisto = 20;
        tipo='b';

    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getAttacco() {
        return attacco;
    }

    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }

    @Override
    public int getCostoAcquisto() {
        return costoAcquisto;
    }

    public void setCostoAcquisto(int costoAcquisto) {
        this.costoAcquisto = costoAcquisto;
    }

    public int getGuadagnoVendita() {
        return guadagnoVendita;
    }

    public void setGuadagnoVendita(int guadagnoVendita) {
        this.guadagnoVendita = guadagnoVendita;
    }

    public int getCostoUpgrade() {
        return costoUpgrade;
    }

    public void setCostoUpgrade(int costoUpgrade) {
        this.costoUpgrade = costoUpgrade;
    }

    @Override
    public int getLivelloTorretta() {
        return livelloTorretta;
    }

    public void setLivelloTorretta(int livelloTorretta) {
        this.livelloTorretta = livelloTorretta;
    }

    @Override
    public int getVelocitàAttacco() {
        return velocitàAttacco;
    }

    public void setVelocitàAttacco(int velocitàAttacco) {
        this.velocitàAttacco = velocitàAttacco;
    }

    public int getTemposparo() {
        return temposparo;
    }

    public void setTemposparo(int temposparo) {
        this.temposparo = temposparo;
    }

    public int getFinestrasparo() {
        return finestrasparo;
    }

    public void setFinestrasparo(int finestrasparo) {
        this.finestrasparo = finestrasparo;
    }

    @Override
    public ArrayList<Proiettile> getProiettili() {
        return proiettili;
    }

    public void setProiettili(ArrayList<Proiettile> proiettili) {
        this.proiettili = proiettili;
    }

    @Override
    public Ellipse2D.Double getRange() {
        return range;
    }

    public void setRange(Ellipse2D.Double range) {
        this.range = range;
    }


    @Override
    public void attacca(Mob bersaglio) {
        proiettili.add(new Proiettile(bersaglio, this.x, this.y, attacco,this));

      
    }
    @Override
      public void colpisci(Mob bersaglio){
          
          if(bersaglio.getTempoRallentato()<=0){
              bersaglio.setRallentato(true);
          bersaglio.setDebuffVelocità(attacco);
      bersaglio.setVelocità(bersaglio.getVelocità()+ attacco);
        }
          bersaglio.setTempoRallentato(bersaglio.getTempoRallentato()+2000);
    }


    @Override
    public void upgrade() {
        System.out.println("mannaggiasanda");

        livelloTorretta = 1;
        attacco += 5;
        velocitàAttacco = 4500;
        giocatore.setSoldi(giocatore.getSoldi() - 20);
        iPanel.update(giocatore);
        sPanel.aggiornamentoStatTor(livelloTorretta, costoAcquisto, velocitàAttacco, attacco);

    }

   
    @Override
    public void disegna(Graphics g) {
        g.drawImage(Pannello.Tipo_torr2, x, y, 40, 40, null);
        g.setColor(Color.YELLOW);
        g.drawOval(x - 40, y - 40, 120, 120);
    }

    @Override
    public void nelRange(Mob bersaglio) {
        if (!bersaglio.isMorto() && range.intersects(bersaglio) && 
            panel.getContTorr()[panel.getTorrette().indexOf(this)] == velocitàAttacco) {
            attacca(bersaglio);
            panel.getContTorr()[panel.getTorrette().indexOf(this)]=0;
        }    
    }

  
}
