/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambiente;

import attori.Torretta;
import java.awt.*;
import static finestre.Finestra.panel;
import static utilities.Valore.terrenoErba;

/**
 *
 * @author User
 */
public final class Griglia {
    private int dimPiastrelle,worldAltezza,worldLarghezza;
 
    private Piastrella[][] piastrella;
   
    public Griglia(){
        this.dimPiastrelle = 40;
        this.worldAltezza = 13;
        this.worldLarghezza = 20;
        definisci();    
    }
    
    public int getDimPiastrelle() {
        return dimPiastrelle;
    }

    public void setDimPiastrelle(int dimPiastrelle) {
        this.dimPiastrelle = dimPiastrelle;
    }

    public int getWorldAltezza() {
        return worldAltezza;
    }

    public void setWorldAltezza(int worldAltezza) {
        this.worldAltezza = worldAltezza;
    }

    public int getWorldLarghezza() {
        return worldLarghezza;
    }

    public void setWorldLarghezza(int worldLarghezza) {
        this.worldLarghezza = worldLarghezza;
    }

    public Piastrella[][] getPiastrella() {
        return piastrella;
    }

    public void setPiastrella(Piastrella[][] piastrella) {
        this.piastrella = piastrella;
    }
    
    public void definisci(){
        piastrella=new Piastrella[worldAltezza][worldLarghezza];
        for (int i=0;i<piastrella.length;i++){
            for(int j=0;j<piastrella[0].length;j++){
                piastrella[i][j]=new Piastrella(j*dimPiastrelle,i*dimPiastrelle,dimPiastrelle,dimPiastrelle,0,0);
            }
        }
    }
       
        
    public void disegna(Graphics g){
       for (Piastrella[] piastrella1 : piastrella) {
           for (int j = 0; j<piastrella[0].length; j++) {
               piastrella1[j].disegna(g);
           }
       }   
    }
        
    public boolean giustaposizione(int x, int y){
        for (Torretta tor: panel.getTorrette())
        {System.out.println(x+" "+ tor.getX());
        
            if (x>tor.getX() && x < tor.getX()+40 && y-40> tor.getY() && y-40 < tor.getY()+40)
            {
                return false;
            }else {
                if(x==tor.getX() || x == tor.getX()+40 || y-40== tor.getY() || y-40 == tor.getY()+40){
                return false;
            }}
                
            
        }
        return piastrella[y/40-1][x/40].getTerrenoId()==terrenoErba;
    }
}
