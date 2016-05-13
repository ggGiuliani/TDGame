/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.*;

public class ConnessioneDB {

    private Connection con;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private int i = 0;
    private String[] classifica;

    public ConnessioneDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/towerdefense", "root", "");
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(" Errore: " + ex);
        }
    }

    public String[] getData() {
        try {
            classifica = new String[10];
            String query = "Select * from giocatore order by punteggio ASC";
            rs = st.executeQuery(query);

            while (rs.next()) {
                String nome = rs.getString("nome");
                String punteggio = rs.getString("punteggio");

                classifica[i] = ("  Giocatore:  " + nome + "   -    Punteggio:  " + punteggio);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return (classifica);
    }

    public void aggiungiDato(String nome, String tempo) {

        try {
            // String query="INSERT INTO giocatore (nome,punteggio) VALUES ('mario',00:03:00);";
            pst = con.prepareStatement("INSERT INTO giocatore (nome,punteggio) VALUES ('" + nome + "','" + tempo + "');");
            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
