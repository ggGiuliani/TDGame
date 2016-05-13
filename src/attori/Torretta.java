/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attori;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public abstract class Torretta extends Rectangle {

    protected int id, attacco, costoAcquisto, guadagnoVendita,
            costoUpgrade, livelloTorretta;
    protected int velocitàAttacco;

   
    protected char tipo;

  
    
      protected int temposparo, finestrasparo;
    protected ArrayList<Proiettile> proiettili;
    protected Ellipse2D.Double range;
        
    public abstract char getTipo();

   public abstract int getCostoAcquisto();
   public abstract ArrayList<Proiettile> getProiettili();
   public abstract int getLivelloTorretta();
   public abstract int getVelocitàAttacco();
   public abstract int getAttacco();
   public abstract int getCostoUpgrade();
   public abstract Ellipse2D.Double getRange();

    public abstract void disegna(Graphics g);

    public abstract void attacca(Mob bersaglio);
    public abstract void colpisci(Mob bersaglio);
    public abstract void nelRange(Mob bersaglio);



    public abstract void upgrade();

   


   
}
