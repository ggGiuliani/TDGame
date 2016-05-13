/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascoltatori;

/**
 *
 * @author User
 */
import java.awt.event.*;
import javax.swing.*;
import static finestre.Finestra.*;

/**
 *
 * @author User
 */
public class AscoltatoreDiEventiInferiore implements ActionListener {

    private JButton b;
    private String stringBot;

    private JLabel l;

    private boolean primoclick = false;
    private boolean primaondata=true;
    public AscoltatoreDiEventiInferiore(JButton button, JLabel label) {
        super();
        b = button;
        stringBot = b.getText();
        l = label;
        //System.out.println(stringBot);
    }
    // deve farsi dare come parametro la Jlabel su 
    // cui dovrà andare ad agire 

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (stringBot) {
            case "READY": {
                panel.setReady(true);
                if (primoclick) {
                    iPanel.getBarraProgresso().setValue(99);
                    
                   
                   
                }
                if(primaondata){
                     iPanel.setOndManc(iPanel.getOndManc()-1);
                     primaondata=false;
                      iPanel.getOndateMancanti().setText("Mancano "+ (iPanel.getOndManc())+ " ondate");
                }
               
               
                 
                iPanel.getReady().setVisible(false);
                primoclick = true;
                panel.setProg(true);

                break;
            }
            case "lento": {
                try {
                    panel.setTempobarra(270);
                    l.setText("lento");
                    System.out.println("LENTO UN BEL Pò");
                } catch (Exception ex) {
                    System.out.println("non funge");
                }
                break;
            }
            case "normale": {
                panel.setTempobarra(130);
                l.setText("normale");
                System.out.println("normale UN BEL Pò");
                break;
            }
            case "veloce": {
                panel.setTempobarra(60);
                l.setText("veloce");
                System.out.println("Veloce UN BEL Pò");
                break;
            }
            default:
                System.out.println("NADA");
        }
    }
}
