/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascoltatori;

import finestre.FinestraIniziale;
import finestre.Finestra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static finestre.Finestra.s;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utilities.TDGAME.connessione;

import static utilities.TDGAME.finestra;
import static utilities.TDGAME.finestraIn;

/**
 *
 * @author Luca
 */
public class AscoltatoreDiEventiGameOver implements ActionListener {

    private JButton b;
    private String stringBot;

    public AscoltatoreDiEventiGameOver(JButton button) {
        super();
        b = button;
        stringBot = b.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (stringBot) {
            case "ricomincia": {

                finestra.dispose();

                finestra = new Finestra();

                break;
            }

            case "chiudi": {
                System.exit(0);
                break;
            }
            case "fine": {

                String nome;

                nome = JOptionPane.showInputDialog(finestra, "Come ti chiami?", "Nome", JOptionPane.QUESTION_MESSAGE);

                String output = "Hai scritto: \"" + nome;
                JOptionPane.showMessageDialog(finestra, output);
                connessione.aggiungiDato(nome, s);
                finestra.dispose();
                finestraIn.dispose();

            try {
                finestraIn = new FinestraIniziale();
            } catch (IOException ex) {
                System.out.println(ex);
            }
                break;
            }
            default:
                System.out.println("NADA");
        }
    }
}
