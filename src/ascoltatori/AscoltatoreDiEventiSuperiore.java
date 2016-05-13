/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascoltatori;

import attori.Torretta1;
import attori.Torretta2;
import java.awt.*;
import java.awt.event.*;
import static finestre.Finestra.*;

public class AscoltatoreDiEventiSuperiore implements MouseListener, MouseMotionListener {

    public AscoltatoreDiEventiSuperiore() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        panel.setDrag(-1);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {

            System.out.println("ci sono");
            if (giocatore.getSoldi() >= panel.getTortipo()[panel.getDrag()].getCostoAcquisto()
                    && panel.getGriglia().giustaposizione(e.getX(), e.getY() )
                    && panel.getDrag() != -1) {

                switch (panel.getDrag()) {
                    case 0:
                        panel.getTorrette().add(new Torretta1(15, e.getX() / 40, e.getY() / 40, panel.getProiettili()));
                        panel.getContTorr()[panel.getTorrette().size()-1]=0;
                        giocatore.setSoldi(giocatore.getSoldi() - panel.getTortipo()[panel.getDrag()].getCostoAcquisto());
                        break;
                    case 1:
                        panel.getTorrette().add(new Torretta2(5, e.getX() / 40, e.getY() / 40, panel.getProiettili()));
                        panel.getContTorr()[panel.getTorrette().size()-1]=0;
                        giocatore.setSoldi(giocatore.getSoldi() - panel.getTortipo()[panel.getDrag()].getCostoAcquisto());
                    break;
                }

                iPanel.update(giocatore);

            }
            panel.setDrag(-1);
        } catch (Exception ec) {
            System.out.println("Pannello sbagliato");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        for (int i = 0; i < 8; i++) {
            if (i < panel.getTortipo().length) {
                if (sPanel.getNegozio().getBottone()[i].contains(sPanel.getTopo()) && giocatore.getSoldi() >= panel.getTortipo()[i].getCostoAcquisto()) {
                    panel.setDrag(i);
                    panel.getTortipo()[i].x=(e.getX() - 20);
                    panel.getTortipo()[i].y=(e.getY() - 60);
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        sPanel.setTopo(new Point((e.getX()), e.getY()));
    }
}
