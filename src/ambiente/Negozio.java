package ambiente;

import java.awt.*;
import static finestre.Finestra.*;

public final class Negozio {

    private final int larghezzaNegozio;
    private final int dimBottone;
    private final int spazioCelle;

    private final Rectangle[] bottone;

    public Rectangle[] getBottone() {
        return bottone;
    }

    public Negozio() {
        larghezzaNegozio = 8;
        dimBottone = 32;
        spazioCelle = 5;
        bottone = new Rectangle[larghezzaNegozio];

        definisci();
    }

    public void definisci() {

        for (int i = 0; i < bottone.length; i++) {

            bottone[i] = new Rectangle(dimBottone + (dimBottone + spazioCelle) * i, 4, dimBottone, dimBottone);
        }
    }

    public void disegna(Graphics g, Point topo, Image[] ris, Image[] tor) {
        int i = 0;
        for (Rectangle bottone1 : bottone) {
            if (bottone1.contains(topo)) {

                switch (i) {
                    case 0:
                        if (giocatore.getSoldi() >= panel.getTortipo()[i].getCostoAcquisto()) {

                            g.drawImage(ris[1], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            if (i < tor.length) {
                                g.drawImage(tor[i], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            }
                        } else {
                            g.drawImage(ris[2], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            if (i < tor.length) {
                                g.drawImage(tor[i], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            }
                        }
                        break;
                    case 1:
                        if (giocatore.getSoldi() >= panel.getTortipo()[i].getCostoAcquisto()) {

                            g.drawImage(ris[1], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            if (i < tor.length) {
                                g.drawImage(tor[i], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            }
                        } else {
                            g.drawImage(ris[2], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            if (i < tor.length) {
                                g.drawImage(tor[i], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                            }
                        }
                        break;
                    default:
                        g.drawImage(ris[0], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                        break;
                }
            } else {

                g.drawImage(ris[0], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                if (i < tor.length) {
                    g.drawImage(tor[i], bottone1.x, bottone1.y, bottone1.width, bottone1.height, null);
                }
            }
            i++;
        }
    }
}
