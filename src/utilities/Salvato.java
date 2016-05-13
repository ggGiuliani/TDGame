/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import ambiente.Griglia;
import ambiente.Piastrella;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Salvato {

    public void caricaSalvato(File caricaPercorso, Griglia griglia) {
        
        try (Scanner caricaScanner = new Scanner(caricaPercorso) // caricaPercorso viene considerato pubblico
                ) {
            while (caricaScanner.hasNext()) {
                for (Piastrella[] piastrella : griglia.getPiastrella()) {
                    for (int j = 0; j < griglia.getPiastrella()[0].length; j++) {
                        piastrella[j].setTerrenoId(caricaScanner.nextInt());
                    }
                }

                for (Piastrella[] piastrella : griglia.getPiastrella()) {
                    for (int j = 0; j < griglia.getPiastrella()[0].length; j++) {
                        piastrella[j].setAriaId(caricaScanner.nextInt());
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
     
}
