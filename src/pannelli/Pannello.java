package pannelli;

import finestre.*;
import ascoltatori.AscoltatoreDiEventiTorretta;
import attori.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import ambiente.*;
import utilities.Salvato;
import static finestre.Finestra.*;
import static utilities.TDGAME.finestra;

@SuppressWarnings("serial")
public class Pannello extends JPanel implements Runnable // implements Runnable ci permette di sfruttare i thread e lo facciamo perchè senza i thread ci è impossibile effettuare il rinfresco dello schermo grazie al paint() bloccato dal ciclo infinito presente nel metodo start() che può monopolizzare le risorse del sistema rendendo anche impossibile la sospensione dell'applicazione perchè non si può chiamare il metodo stop(). Quindi l'applicazione deve essere scritte utilizzando i thread(processi) che si occupano di determinate operazioni indipendenti da thread differenti..
{

    public Thread thread; // definiamo una variabile di istanza che contenga il thread dell'applicazione
    public static Image[] Tipo_terreno;
    public static Image[] Tipo_aria;
    public static Image[] Tipo_acqua ;
    public static Image Tipo_mob;
    public static Image Tipo_torr1; 
    public static Image Tipo_torr2 ;
    private volatile boolean running;

   
    private boolean primoControl = true;
    private int a, idmob;
    private PopUpGameOver fine;
    public int myWidth, myHeight;
    private boolean ready;

    private Griglia griglia;

    private Torretta[] tortipo;
    
    private int contond;
    private int max;
    private int velMob;
    private int vitaMob;
    private int mobMax;
    
    
    private int mobUccisi;
    private boolean prog,fin;

    private int drag;

    private ArrayList<Torretta> torrette;
    private ArrayList<Proiettile> proiettili;
    private ArrayList<Ondata> ond;
    private int[] contTorr;


    private AscoltatoreDiEventiTorretta ricevitoreTor;

    private Salvato save;

    private Mappa map1;
    private Mappa map2;
    private Mappa map3;

    private int tempospawn, finestraspawn, tempobarra, finestrabarra;

    public Pannello(Finestra finestra) {
        thread = new Thread(this);// definiamo una variabile di istanza che contenga il thread dell'applicazione
    Tipo_terreno = new Image[100];
    Tipo_aria = new Image[100];
    Tipo_acqua = new Image[100];
    Tipo_mob = new ImageIcon("risorse/mob0.png").getImage();
    Tipo_torr1 = new ImageIcon("risorse/laser.png").getImage();
    Tipo_torr2 = new ImageIcon("risorse/laser1.png").getImage();
     ready = false;
     a = 0;
     idmob=1;
     contond = 0;
     max = 4;
     velMob= 12;
     vitaMob=45;
      mobMax = max * 3;

    
      mobUccisi = 0;
      prog = false;

      drag = -1;
      tortipo = new Torretta[2];
        tempospawn = 1200;
        finestraspawn = 0;

        tempobarra = 250;
        finestrabarra = 0;
        fine = new PopUpGameOver();

        ricevitoreTor = new AscoltatoreDiEventiTorretta();
        thread.start(); //inizia l'esecuzione del processo innescando l'invocazione al metodo run() dell'applicazione
    }
    
     public int getMobMax() {
        return mobMax;
    }

    public void setMobMax(int mobMax) {
        this.mobMax = mobMax;
    }
    
    public int getMobUccisi() {
        return mobUccisi;
    }

    public void setMobUccisi(int mobUccisi) {
        this.mobUccisi = mobUccisi;
    }
    
     public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public Torretta[] getTortipo() {
        return tortipo;
    }

    public void setTortipo(Torretta[] tortipo) {
        this.tortipo = tortipo;
    }

    public ArrayList<Proiettile> getProiettili() {
        return proiettili;
    }

    public void setProiettili(ArrayList<Proiettile> proiettili) {
        this.proiettili = proiettili;
    }
    
    public int[] getContTorr() {
        return contTorr;
    }

    public ArrayList<Torretta> getTorrette() {
        return torrette;
    }

    public void setTorrette(ArrayList<Torretta> torrette) {
        this.torrette = torrette;
    }

    public void setProg(boolean prog) {
        this.prog = prog;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getDrag() {
        return drag;
    }

    public void setDrag(int drag) {
        this.drag = drag;
    }

    public ArrayList<Ondata> getOnd() {
        return ond;
    }

    public Griglia getGriglia() {
        return griglia;
    }

    public void setGriglia(Griglia griglia) {
        this.griglia = griglia;
    }

    public void definisci() {
        addMouseListener(ricevitoreTor);
        addMouseMotionListener(ricevitoreTor);
        griglia = new Griglia();

        map1 = new Mappa(griglia.getWorldLarghezza(), griglia.getWorldAltezza());
        map2 = new Mappa(griglia.getWorldLarghezza(), griglia.getWorldAltezza());
        map3 = new Mappa(griglia.getWorldLarghezza(), griglia.getWorldAltezza());

        save = new Salvato();
        for (int i = 0; i < Tipo_terreno.length; i++) {
            Tipo_terreno[i] = new ImageIcon("risorse/tipo_terreno.png").getImage();//ImageIcon è un'implementazione dell'interfaccia icona che dipinge le icone dalle immagini prese da un array di url o nome del file precaricate tramite Media Traker per monitorare lo stato caricato dell'immagine. getImage restituisce l'immagine che ottiene i dati dei pixel dal file specificato , il cui formato può essere sia GIF, JPEG o PNG .
            Tipo_terreno[i] = createImage(new FilteredImageSource(Tipo_terreno[i].getSource(), new CropImageFilter(0, 20 * i, 20, 20)));
        }
        for (int i = 0; i < Tipo_aria.length; i++) {
            Tipo_aria[i] = new ImageIcon("risorse/tipo_aria.png").getImage();

            Tipo_aria[i] = createImage(new FilteredImageSource(Tipo_aria[i].getSource(), new CropImageFilter(0, 20 * i, 20, 20)));  //la classe FilteredImageSource è un'implementazione dell'interfaccia ImageProducer(interfaccia di oggetti che possono produrre dati di immagine per l'immagine. Ogni immagine contiene un ImageProducer che viene utilizzato per ricostruire l'immagine ogni volta che è necessario). Essa prende un'immagine esistente e un oggetto filtro e li utilizza per produrre dati di un'immagine nuova filtrata da quella originale.  La classe CropImageFilter estende la classe ImageFilter e serve per estrarre una data regione rettangolare di un'immagine esistente e fornirne una fonte per una nuova immagine che contiene solo la regione estratta. Essa è utilizzata in combinazione con un oggetto FiltereImageSource per produrre versioni ritagliate delle immagini esistenti.

            Tipo_aria[i] = new ImageIcon("risorse/Acqua.png").getImage();
        }

        save.caricaSalvato(new File("salvato/missione1_cavalli.txt"), griglia);

        map1.percorso(new File("salvato/mappa1.txt"));
        map2.percorso(new File("salvato/mappa2.txt"));
        map3.percorso(new File("salvato/mappa3.txt"));
        
        ond = new ArrayList<>();
        ond.add(new Ondata(map1, max, vitaMob, velMob, griglia.getDimPiastrelle(),Tipo_mob));
        ond.add(new Ondata(map2, max, vitaMob, velMob, griglia.getDimPiastrelle(),Tipo_mob));
        ond.add(new Ondata(map3, max, vitaMob, velMob, griglia.getDimPiastrelle(),Tipo_mob));

        torrette = new ArrayList<>();
        proiettili = new ArrayList<>();
        contTorr= new int[100];

        tortipo[0] = new Torretta1(0, 80, 80, proiettili);
        tortipo[1] = new Torretta2(0, 80, 80, proiettili);

    }

    public int getTempobarra() {
        return tempobarra;
    }

    public void setTempobarra(int tempobarra) {
        this.tempobarra = tempobarra;
    }

    @Override
    public void paintComponent(Graphics g)//g è l'oggetto che disegna ciò che gli ordiniamo
    {
        if (primoControl) {
            myWidth = getWidth();
            myHeight = getHeight();
            definisci();
            primoControl = false;
        }

        g.clearRect(0, 0, getWidth(), getHeight());
        griglia.disegna(g);

        for (int i = a; i < ond.size(); i++) {
            for (Mob mob : ond.get(i).getMobs()) {
                if (mob.isIngioco()) {
                    mob.disegna(g);
                }
            }
          
        }

       

        if (drag != -1) {
            tortipo[drag].disegna(g);
        } 
        
        torrette.stream().map((tor) -> {
            tor.disegna(g);
            return tor;
        }).forEach((Torretta tor) -> {
            try {
                tor.getProiettili().stream().filter((p) -> (p.isSparato() && !p.isColpito())).forEach((p) -> {
                    p.disegna(g);
                });
            } catch (Exception e) {
                System.out.println("c'è un problema con i proiettili "+e);
            }
        });
    }

    //il metodo che segue si occupa dello spawn dei mob verificando prima che essi non siano in gioco definendo anche un ritardo(delay) di spawn tra loro grazie al conteggio dei frame
    public void progressoBarra() {
        if (finestrabarra >= tempobarra) {
            iPanel.getBarraProgresso().setValue(iPanel.getBarraProgresso().getValue() + 1);
            
            if (iPanel.getBarraProgresso().getValue() >= 100) {
                if(iPanel.getOndManc()>0){
                iPanel.setOndManc(iPanel.getOndManc()-1);
                if(iPanel.getOndManc()==0)
                iPanel.getOndateMancanti().setText("Ondate terminate! :-)");  
                else
                iPanel.getOndateMancanti().setText("Mancano "+ (iPanel.getOndManc())+ " ondate");
                
                } 
                iPanel.getBarraProgresso().setValue(0);
                
                
                vitaMob+=20;
                velMob-=1;
                if (contond >= 6) {
                    iPanel.getReady().setVisible(false);
                    iPanel.remove(iPanel.getBarraProgresso());
                   
                } else {
                    max +=3;
                   
                    mobMax += max * 3;
                    ond.add(new Ondata(map1, max, vitaMob, velMob, griglia.getDimPiastrelle(), Tipo_mob= new ImageIcon("risorse/mob"+idmob+".png").getImage() ));
                    ond.add(new Ondata(map2, max, vitaMob, velMob,griglia.getDimPiastrelle(),Tipo_mob= new ImageIcon("risorse/mob"+idmob+".png").getImage() ));
                    ond.add(new Ondata(map3, max, vitaMob, velMob, griglia.getDimPiastrelle(),Tipo_mob= new ImageIcon("risorse/mob"+idmob+".png").getImage() ));
                    iPanel.getReady().setVisible(false);
                    contond += 3;
                    idmob+=1;
                    finestraspawn = 799;
                   
                }
            }
            finestrabarra = 0;
        } else {
            finestrabarra += 1;
        }
    }

    public void evocatore() {
        if (finestraspawn >= tempospawn) {
            for (int i = a; i < ond.size(); i++) {
                for (Mob mob : ond.get(i).getMobs()) {
                    if (!mob.isIngioco() && !mob.isMorto()) {
                        mob.spawn();
                        break;
                    }
                }

            }
            finestraspawn = 0;

        } else {
            finestraspawn += 1;

        } 
    }

    @Override
    public void run()// il metodo run contiene il codice che descrive il funzionamento dell'applicazione
    {
      running=true;
        while (running) {
            if (!primoControl) {
                if (ready) {
                    evocatore(); 
                    
                    
                    
                   
                }// chiama il metodo evocatore
                if (prog) {
                    progressoBarra();
                }
               
                try{if (ond.get(contond).getMobs()[max -1].isIngioco() && contond < 6 ) {
                    iPanel.getReady().setVisible(true);
                    
                     
                }}catch(Exception di){System.out.println("..." + di);}

                for (int i = a; i < ond.size(); i++) {
                    
                    
                    for (Mob mob : ond.get(i).getMobs()) {

                        //questo ciclo serve per scorrere tutti i mob dell'ondata fino a completare la lunghezza di quest'ultima
                        if (mob.isIngioco()) {
                            // verifico che il mob i-esimo dell'ondata corrente è in gioco
                            mob.avanzamento(ond.get(i).getMappa().getMatricePercorso()); //chiamo il metodo avanzamento dell'i-esimo mob della relativa ondata
                            if(mob.getTempoRallentato()>0){
                            mob.setTempoRallentato(mob.getTempoRallentato()-1);}
                            else  if(mob.getTempoRallentato()<=0 && mob.isRallentato()){
                                     mob.setRallentato(false);
                                 mob.setVelocità(mob.getVelocità()-mob.getDebuffVelocità());
                                 }
                        
                           
                                for (Torretta tor : torrette) {
                        
                                    if (contTorr[torrette.indexOf(tor)]!=(tor.getVelocitàAttacco())){
                                        contTorr[torrette.indexOf(tor)] += 1;
                                    }
                                    
                                    tor.nelRange(mob);
                                

                                }
                           
                        }
                    }
                    
                }
                    

                
try{                torrette.stream().forEach((tor) -> {
                    tor.getProiettili().stream().filter((p) -> (p.isSparato())).forEach((p) -> {
                        p.calcolaDirezione();
                    });
                });}catch (Exception ecc){System.out.println("crash proiettili"+ecc);}
                

                if (giocatore.getVita() <= 0) {
                    finestra.getTimer().stop();
                    fine.HaiPerso();
                    running=false;

                }

                if (mobUccisi == mobMax && contond >= 6) {
                    finestra.getTimer().stop();

                    fine.HaiVinto();
                    running=false;

                }

            }
            /*il metodo repaint esegue la chiamata al metodo update()(che cancella la parte di schermo dedicata
            all'applicazione dipingendo tutto con il colore di sfondo causando un possibile sfarfallio nelle animazioni) 
            poi di conseguenza al metodo paint() al termine delle operazione di preparazione dell'immagine
            (il metodo paint() è un metodo chiamato automaticamente da Java tutte le volte che risulta necessario
            aggiornare la zona del video dedicata all'applicazione).*/
            repaint();

            try {
                Thread.sleep(1);//Thread.sleep fa sì che il thread corrente sospenda l'esecuzione per un periodo determinato . Si tratta di uno strumento efficace per dare tempo al processore per gestire altri thread di un'applicazione o di altre applicazioni che potrebbero essere in esecuzione su un sistema informatico .
            } catch (Exception e) {
                System.out.println("BABUDOIO");
            }
        } running=false;
    }

   

}
