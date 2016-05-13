/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambiente;

import pannelli.Pannello;
import java.awt.*;
import utilities.Valore;

/**
 *
 * @author User
 */
public class Piastrella extends Rectangle {

    private int terrenoId, ariaId;

    public Piastrella(int x, int y, int larghezza, int altezza, int terrenoId, int ariaId) {
        setBounds(x, y, larghezza, altezza);//Sposta e ridimensiona questo componente(Un componente è un oggetto con una rappresentazione grafica che può essere visualizzato sullo schermo e che può interagire con l'utente):
        this.terrenoId = terrenoId;
        this.ariaId = ariaId;
    }

    public int getTerrenoId() {
        return terrenoId;
    }

    public void setTerrenoId(int terrenoId) {
        this.terrenoId = terrenoId;
    }

    public int getAriaId() {
        return ariaId;
    }

    public void setAriaId(int ariaId) {
        this.ariaId = ariaId;
    }

    public void disegna(Graphics g) {

        g.drawImage(Pannello.Tipo_terreno[terrenoId], x, y, width, height, null);
        if (ariaId != Valore.ariaAria) {
            g.drawImage(Pannello.Tipo_aria[ariaId], x, y, width, height, null);//Disegna la maggior quantità di immagine specificata come è già stato ridimensionata per adattarsi all'interno del rettangolo specificato .
        }
    }

}
