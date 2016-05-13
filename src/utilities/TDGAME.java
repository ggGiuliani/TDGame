/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import finestre.FinestraIniziale;
import finestre.Finestra;
import java.io.IOException;

/**
 *
 * @author User
 */
public class TDGAME {

    public static Finestra finestra;
    public static FinestraIniziale finestraIn;
    public static ConnessioneDB connessione;

    public static void main(String args[]) throws IOException {
        connessione = new ConnessioneDB();
        finestraIn = new FinestraIniziale();

    }

}
