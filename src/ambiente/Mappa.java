/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambiente;

import java.io.*;
import java.util.Scanner;
import utilities.Posizione;

/**
 *
 * @author User
 */
public class Mappa {

    private Posizione partenza;
    private final int[][] matricePercorso;

    public Posizione getPartenza() {
        return partenza;
    }

    public int[][] getMatricePercorso() {
        return matricePercorso;
    }
    
    public Mappa(int larghezza,int altezza){
        this.matricePercorso = new int[altezza][larghezza];
 
    }
    
    public void percorso(File cPercorso){
        try(Scanner caricaScanner = new Scanner(cPercorso)) {
            int temp;
            
            while(caricaScanner.hasNext())
            {
                for(int i=0; i < matricePercorso.length; i++)
                {
                    for(int j=0; j< matricePercorso[0].length;j++)
                    {
                        temp=caricaScanner.nextInt();

                        matricePercorso[i][j]=temp;

                        if (temp==2)
                        {
                           partenza= new Posizione(i,j);
                        }
                    }
                }
            }
        } 
        catch(Exception e)
        {
            System.out.println("ciao c'è qualquadra che non cosa");
        }       
    }   
}

                   