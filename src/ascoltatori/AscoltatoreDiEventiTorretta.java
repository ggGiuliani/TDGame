/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascoltatori;

import attori.Torretta;
import java.awt.event.*;
import javax.swing.JButton;
import static finestre.Finestra.*;

/**
 *
 * @author Giacomo
 */
public class AscoltatoreDiEventiTorretta implements ActionListener, MouseListener, MouseMotionListener {

    private JButton b;
    private String stringaBot;

    public AscoltatoreDiEventiTorretta() {
        super();

    }

    public AscoltatoreDiEventiTorretta(JButton bottone) {
        super();
        b = bottone;

        stringaBot = b.getText();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (stringaBot) {

            case "UPGRADE": {
                try {
                    sPanel.getTorrettaSelezionata().upgrade();

                   
                } catch (Exception ex) {
                    System.out.println("non funge");
                }
                break;
            }
            case "VENDI": {
                try {
                    panel.getTorrette().remove(sPanel.getTorrettaSelezionata());
                    sPanel.caratteristicheTor();
                    giocatore.aggiungiSoldi(sPanel.getTorrettaSelezionata().getCostoAcquisto()/ 2);
                    iPanel.update(giocatore);

                    System.out.println("HO VENDUTO");
                } catch (Exception es) {
                    System.out.println("non vende");
                }
                break;
            }
            default:
                System.out.println("SHISH");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        sPanel.caratteristicheTor();
        for (Torretta tor : panel.getTorrette()) {

            if (e.getX() >= tor.getX() && e.getX() <= tor.getX() + 40 && e.getY() >= tor.getY() && e.getY() <= tor.getY() + 40) {

                sPanel.setTorrettaSelezionata(tor);
                if ((sPanel.getTorrettaSelezionata().getLivelloTorretta() == 0)) {
                    sPanel.caratteristicheTor(tor.getLivelloTorretta(), tor.getCostoAcquisto(), tor.getVelocitàAttacco(), tor.getAttacco());

                } else {
                    sPanel.caratteristicheTor(tor.getLivelloTorretta(), tor.getCostoAcquisto(), tor.getVelocitàAttacco(), tor.getAttacco());
                    sPanel.getUpGrade().setVisible(false);
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
