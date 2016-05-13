/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascoltatori;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JOptionPane;

import static utilities.TDGAME.finestraIn;

/**
 *
 * @author User
 */
public class AscoltatoreInfo implements MouseListener,ActionListener{
private JOptionPane popup;
public AscoltatoreInfo(){
popup=new JOptionPane();
}
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            popup.showMessageDialog(finestraIn,finestraIn.getDs().stampaIlFile());
        } catch (IOException ex) {System.out.println(ex+"asdasdas");
            
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
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
