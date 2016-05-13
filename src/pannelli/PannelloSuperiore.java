/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pannelli;

import ascoltatori.AscoltatoreDiEventiTorretta;
import ascoltatori.AscoltatoreDiEventiSuperiore;
import attori.Giocatore;
import attori.Torretta;

import java.awt.*;
import javax.swing.*;
import ambiente.Negozio;
import static finestre.Finestra.giocatore;
import static finestre.Finestra.sPanel;

public class PannelloSuperiore extends JPanel implements Runnable {

    private JLabel costoTorretta, livello, danno, velocità;
    private JButton upGrade, vendi;

    public JButton getUpGrade() {
        return upGrade;
    }

    public void setUpGrade(JButton upGrade) {
        this.upGrade = upGrade;
    }

    public JButton getVendi() {
        return vendi;
    }

    public void setVendi(JButton vendi) {
        this.vendi = vendi;
    }
    private AscoltatoreDiEventiSuperiore ricevitore;
    private AscoltatoreDiEventiTorretta ricevitoreU, ricevitoreV;
    private Torretta torrettaSelezionata;

    public Torretta getTorrettaSelezionata() {
        return torrettaSelezionata;
    }

    public void setTorrettaSelezionata(Torretta torrettaSelezionata) {
        this.torrettaSelezionata = torrettaSelezionata;

    }
    private final Thread threadTop;
    private Negozio negozio;

    private Image[] setTipo_ris = new Image[100];
    private Image[] setTipo_tor = new Image[100];

    public Image[] getSetTipo_tor() {
        return setTipo_tor;
    }

    public void setSetTipo_tor(Image[] setTipo_tor) {
        this.setTipo_tor = setTipo_tor;
    }

    public AscoltatoreDiEventiSuperiore getRicevitore() {
        return ricevitore;
    }

    public void setRicevitore(AscoltatoreDiEventiSuperiore ricevitore) {
        this.ricevitore = ricevitore;
    }

    public AscoltatoreDiEventiTorretta getRicevitoreU() {
        return ricevitoreU;
    }

    public void setRicevitoreU(AscoltatoreDiEventiTorretta ricevitoreU) {
        this.ricevitoreU = ricevitoreU;
    }

    public AscoltatoreDiEventiTorretta getRicevitoreV() {
        return ricevitoreV;
    }

    public void setRicevitoreV(AscoltatoreDiEventiTorretta ricevitoreV) {
        this.ricevitoreV = ricevitoreV;
    }

    public JLabel getCostoTorretta() {
        return costoTorretta;
    }

    public void setCostoTorretta(JLabel costoTorretta) {
        this.costoTorretta = costoTorretta;
    }

    public JLabel getLivello() {
        return livello;
    }

    public void setLivello(JLabel livello) {
        this.livello = livello;
    }

    public JLabel getDanno() {
        return danno;
    }

    public void setDanno(JLabel danno) {
        this.danno = danno;
    }

    public JLabel getVelocità() {
        return velocità;
    }

    public void setVelocità(JLabel velocità) {
        this.velocità = velocità;
    }

    private boolean primoControl;

    public Negozio getNegozio() {
        return negozio;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }

    public Image[] getSetTipo_ris() {
        return setTipo_ris;
    }

    public void setSetTipo_ris(Image[] setTipo_ris) {
        this.setTipo_ris = setTipo_ris;
    }
    private Point topo = new Point(0, 0);

    public Point getTopo() {
        return topo;
    }

    public void setTopo(Point topo) {
        this.topo = topo;
    }

    public PannelloSuperiore(Giocatore giocatore) {
        super();
        primoControl = true;
        threadTop = new Thread(this);

        velocità = new JLabel("velocità attacco torretta :");
        danno = new JLabel("danno torretta: ");
        costoTorretta = new JLabel("costo torretta: ");
        livello = new JLabel("livello torretta: ");
        upGrade = new JButton("UPGRADE");
        vendi = new JButton("VENDI");
        ricevitore = new AscoltatoreDiEventiSuperiore();
        ricevitoreU = new AscoltatoreDiEventiTorretta(upGrade);
        ricevitoreV = new AscoltatoreDiEventiTorretta(vendi);

        setPreferredSize(new Dimension(800, 40));

        threadTop.start();
    }

    public void definisci() {
        negozio = new Negozio();

        setTipo_ris[0] = new ImageIcon("risorse/cella.png").getImage();
        setTipo_ris[1] = new ImageIcon("risorse/cellaUp.png").getImage();
        setTipo_ris[2] = new ImageIcon("risorse/cellaDown.png").getImage();
        setTipo_tor[0] = new ImageIcon("risorse/laser.png").getImage();
        setTipo_tor[1] = new ImageIcon("risorse/laser1.png").getImage();

        addMouseListener(ricevitore);
        addMouseMotionListener(ricevitore);

        add(livello);
        add(costoTorretta);
        add(velocità);
        add(danno);
        add(upGrade);
        add(vendi);

        upGrade.addActionListener(ricevitoreU);
        vendi.addActionListener(ricevitoreV);
        upGrade.setVisible(false);
        vendi.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (primoControl) {
            definisci();
            primoControl = false;
        }
        g.clearRect(0, 0, getWidth(), getHeight());
        negozio.disegna(g, getTopo(), getSetTipo_ris(), getSetTipo_tor());
    }

    @Override
    public void run() {

        while (true) {

            if (!primoControl) {

                repaint();
            }//il metodo repaint esegue la chiamata al metodo update()(che cancella la parte di schermo dedicata all'applicazione dipingendo tutto con il colore di sfondo causando un possibile sfarfallio nelle animazioni) poi di conseguenza al metodo paint() al termine delle operazione di preparazione dell'immagine(il metodo paint() è un metodo chiamato automaticamente da Java tutte le volte che risulta necessario aggiornare la zona del video dedicata all'applicazione).
            try {
                threadTop.sleep(1);//Thread.sleep fa sì che il thread corrente sospenda l'esecuzione per un periodo determinato . Si tratta di uno strumento efficace per dare tempo al processore per gestire altri thread di un'applicazione o di altre applicazioni che potrebbero essere in esecuzione su un sistema informatico .
            } catch (Exception e) {
            }
        }
    }

    public void caratteristicheTor(int liv, int costo, int vel, int dan) {

        velocità.setText("velocità attacco torretta :" + vel);
        danno.setText("danno torretta: " + dan);
        livello.setText("livello torretta: " + liv);
        costoTorretta.setText("costo torretta: " + costo);

        vendi.setVisible(true);
if(giocatore.getSoldi()>=sPanel.getTorrettaSelezionata().getCostoUpgrade())
        upGrade.setVisible(true);
    }

    public void caratteristicheTor() {

        velocità.setText("velocità attacco torretta :");
        danno.setText("danno torretta: ");
        livello.setText("livello torretta: ");
        costoTorretta.setText("costo torretta: ");

        upGrade.setVisible(false);
        vendi.setVisible(false);
    }

    public void aggiornamentoStatTor(int liv, int costo, int vel, int dan) {
        velocità.setText("velocità attacco torretta :" + vel);
        danno.setText("danno torretta: " + dan);
        livello.setText("livello torretta: " + liv);
        costoTorretta.setText("costo torretta: " + costo);
        upGrade.setVisible(false);
        vendi.setVisible(true);
    }
}
