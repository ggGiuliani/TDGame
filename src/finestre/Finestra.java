/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finestre;

import attori.Giocatore;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import pannelli.*;

public class Finestra extends JFrame {

    private Timer timer;

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    private long startTime;
    public static Giocatore giocatore;

    public static Pannello panel;
    public static PannelloSuperiore sPanel;
    public static PannelloInferiore iPanel;
    public static JPanel cronometro;
    public static String s;

    public Finestra() {
        super("esempio");

        giocatore = new Giocatore(100, 30);
        panel = new Pannello(this);
        sPanel = new PannelloSuperiore(giocatore);
        iPanel = new PannelloInferiore(giocatore);
        cronometro = new JPanel();
        cronometro.setBackground(Color.white);
        Container c = this.getContentPane();

        JLabel labTime;
        labTime = new JLabel("0:00:00.0");
        labTime.setFont(new Font("SansSerif", Font.BOLD, 30));
        labTime.setHorizontalAlignment(JLabel.CENTER);

        JPanel BottoniPanel;
        BottoniPanel = new JPanel(new GridLayout(1, 2));

        cronometro.add(labTime, BorderLayout.CENTER);
        cronometro.add(BottoniPanel, BorderLayout.SOUTH);

        sPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 6, 6));

        c.setLayout(new BorderLayout());

        c.add(getContentPane().add(sPanel), BorderLayout.NORTH);

        c.add(getContentPane().add(iPanel), BorderLayout.SOUTH);
        c.add(getContentPane().add(panel), BorderLayout.CENTER);
        c.add(cronometro, BorderLayout.EAST);

        this.pack();//pack dimensiona il frame in modo da contenere esattamente il pannello 

        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        timer = new Timer(100, (ActionEvent e) -> {
            long diffTime = System.currentTimeMillis() - startTime;
            int decSeconds = (int) (diffTime % 1000 / 100);
            int seconds = (int) (diffTime / 1000 % 60);
            int minutes = (int) (diffTime / 60000 % 60);
            int hours = (int) (diffTime / 3600000);
            s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
            labTime.setText(s);
        });

        startTime = System.currentTimeMillis();
        timer.start();

        this.setVisible(true);
    }
}
