package minivilles.ihm.gui;

import minivilles.metier.Metier;
import minivilles.metier.cartes.Carte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by louis on 21/06/17.
 */

public class Fenetre extends JFrame implements ItemListener, ActionListener {

    private Metier metier; // pas très MVC-friendly

    private int nombreDeJoueurs;
    // Image affichée à côté de la réserve
    private JLabel imageCarte;
    // Nombre de carte de l'image précédente
    private JLabel nombreDeCarte;
    private JComboBox acheterBatimentListe;
    private JComboBox construireMonumentListe;
    private JButton acheterBatimentButton;
    private JButton construireMonumenButton;

    private JPanel joueurA;
    private JPanel joueurB;
    private JPanel joueurC;
    private JPanel joueurD;

    private JButton passerTourButton;
    private JLabel infoCommandes;

    private JLabel pieceJoueurA;
    private JLabel pieceJoueurB;
    private JLabel pieceJoueurC;
    private JLabel pieceJoueurD;

    private JComboBox carteJoueurA;
    private JComboBox carteJoueurB;
    private JComboBox carteJoueurC;
    private JComboBox carteJoueurD;

    private JLabel tourDuJoueur;

    private JLabel imageCarteMonumentA1;
    private JLabel imageCarteMonumentA2;
    private JLabel imageCarteMonumentA3;
    private JLabel imageCarteMonumentA4;

    private JLabel imageCarteMonumentB1;
    private JLabel imageCarteMonumentB2;
    private JLabel imageCarteMonumentB3;
    private JLabel imageCarteMonumentB4;

    private JLabel imageCarteMonumentC1;
    private JLabel imageCarteMonumentC2;
    private JLabel imageCarteMonumentC3;
    private JLabel imageCarteMonumentC4;

    private JLabel imageCarteMonumentD1;
    private JLabel imageCarteMonumentD2;
    private JLabel imageCarteMonumentD3;
    private JLabel imageCarteMonumentD4;

    private JLabel imageDeUn;
    private JLabel imageDeDeux;

    private String[] nomCarteJoueurA;
    private String[] nomCarteJoueurB;
    private String[] nomCarteJoueurC;
    private String[] nomCarteJoueurD;

    private String[] nomCartes = new String[0];

    private String[] nomMonuments = {"Gare", "Centre commercial", "Parc d'attractions", "Tour radio"};

    private Map<String, List<Carte>> pioche;

    private boolean passerTour;
    private boolean construire;
    private boolean acheter;


    public Fenetre(Metier metier) {

        passerTour=false;
        acheter=false;
        construire=false;

        this.metier = metier;

        setTitle("Miniville");
        // Plein écran
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(3);

        JPanel panelGlobal = new JPanel();
        BoxLayout globalLayout = new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS);
        panelGlobal.setLayout(globalLayout);
        panelGlobal.setBorder(new EmptyBorder(0, 0, 0, 0));
        JPanel partieHaute = new JPanel(new FlowLayout());
        JPanel gauche = new JPanel();
        //gauche.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout gaucheLayout = new BoxLayout(gauche, BoxLayout.X_AXIS);
        gauche.setLayout(gaucheLayout);
        JPanel droite = new JPanel();
        //droite.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel partieBasse = new JPanel(new GridLayout(2, 2));
        joueurA = new JPanel();
        //joueurA.setBorder(BorderFactory.createLineBorder(Color.black));
        joueurB = new JPanel();
        //joueurB.setBorder(BorderFactory.createLineBorder(Color.black));
        joueurC = new JPanel();
        //joueurC.setBorder(BorderFactory.createLineBorder(Color.black));
        joueurD = new JPanel();
        //joueurD.setBorder(BorderFactory.createLineBorder(Color.black));
        partieHaute.add(gauche);
        partieHaute.add(droite);
        partieBasse.add(joueurA);
        partieBasse.add(joueurB);
        partieBasse.add(joueurC);
        partieBasse.add(joueurD);
        panelGlobal.add(partieHaute);
        panelGlobal.add(partieBasse);

        // Gauche ///////////////////////////////////////////////////////////////////////////
        JPanel contenantGauche = new JPanel(new BorderLayout());
        nombreDeCarte = new JLabel("Nombre de cette carte dans la réserve : 6");
        nombreDeCarte.setBorder(new EmptyBorder(5, 5, 5, 5));
        imageCarte = new JLabel(new ImageIcon(Art.getImage("cartes/1")));
        contenantGauche.add(imageCarte, BorderLayout.CENTER);
        contenantGauche.add(nombreDeCarte, BorderLayout.SOUTH);
        gauche.add(contenantGauche);
        /////////////////////////////////////////////////////////////////////////////////////


        // Droite ///////////////////////////////////////////////////////////////////////////
        JPanel contenantDroit = new JPanel();
        BoxLayout contenantDroitLayout = new BoxLayout(contenantDroit, BoxLayout.Y_AXIS);
        contenantDroit.setLayout(contenantDroitLayout);

        JPanel boutons = new JPanel(new GridLayout(3, 2, 14, 14));

        acheterBatimentListe = new JComboBox(nomCartes);
        acheterBatimentListe.addItemListener(this);
        boutons.add(acheterBatimentListe);
        acheterBatimentButton = new JButton("Acheter ce bâtiment");
        acheterBatimentButton.addActionListener(this);
        boutons.add(acheterBatimentButton);

        construireMonumentListe = new JComboBox(nomMonuments);
        construireMonumentListe.addItemListener(this);
        boutons.add(construireMonumentListe);
        construireMonumenButton = new JButton("Construire ce monument");
        construireMonumenButton.addActionListener(this);
        boutons.add(construireMonumenButton);

        passerTourButton = new JButton("Passer");
        passerTourButton.addActionListener(this);

        infoCommandes = new JLabel("Informations sur les commandes");

        tourDuJoueur = new JLabel("Tour du joueur numéro ");

        boutons.add(tourDuJoueur);
        boutons.add(passerTourButton);

        JPanel imageDe = new JPanel(new GridLayout(1, 2, 50, 50));
        imageDeUn = new JLabel(new ImageIcon(Art.getImage("des/1")));
        imageDeUn.setBorder(new EmptyBorder(50, 50, 50, 50));
        imageDeDeux = new JLabel(new ImageIcon(Art.getImage("des/1")));
        imageDeDeux.setBorder(new EmptyBorder(45, 45, 45, 45));
        imageDe.add(imageDeUn);
        imageDe.add(imageDeDeux);


        contenantDroit.add(boutons);
        contenantDroit.add(imageDe);
        droite.add(contenantDroit);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur A /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurA = new JPanel();
        // On colore la case du joueur courant
        contenantJoueurA.setBackground(Color.green);

        JPanel infoJoueurA = new JPanel(new GridLayout(3, 1));

        infoJoueurA.add(new JLabel("Joueur numéro 1"));
        pieceJoueurA = new JLabel("Pièces : 3");
        infoJoueurA.add(pieceJoueurA);

        carteJoueurA = new JComboBox(nomCartes);
        infoJoueurA.add(carteJoueurA);

        contenantJoueurA.add(infoJoueurA);

        imageCarteMonumentA1 = new JLabel(new ImageIcon(Art.getImage("cartes/M1EP")));
        contenantJoueurA.add(imageCarteMonumentA1);
        imageCarteMonumentA2 = new JLabel(new ImageIcon(Art.getImage("cartes/M2EP")));
        contenantJoueurA.add(imageCarteMonumentA2);
        imageCarteMonumentA3 = new JLabel(new ImageIcon(Art.getImage("cartes/M3EP")));
        contenantJoueurA.add(imageCarteMonumentA3);
        imageCarteMonumentA4 = new JLabel(new ImageIcon(Art.getImage("cartes/M4EP")));
        contenantJoueurA.add(imageCarteMonumentA4);

        joueurA.add(contenantJoueurA);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur B /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurB = new JPanel();

        JPanel infoJoueurB = new JPanel(new GridLayout(3, 1));

        infoJoueurB.add(new JLabel("Joueur numéro 2"));
        pieceJoueurB = new JLabel("Pièces : 2");
        infoJoueurB.add(pieceJoueurB);

        carteJoueurB = new JComboBox(nomCartes);
        infoJoueurB.add(carteJoueurB);

        contenantJoueurB.add(infoJoueurB);

        imageCarteMonumentB1 = new JLabel(new ImageIcon(Art.getImage("cartes/M1EP")));
        contenantJoueurB.add(imageCarteMonumentB1);
        imageCarteMonumentB2 = new JLabel(new ImageIcon(Art.getImage("cartes/M2EP")));
        contenantJoueurB.add(imageCarteMonumentB2);
        imageCarteMonumentB3 = new JLabel(new ImageIcon(Art.getImage("cartes/M3EP")));
        contenantJoueurB.add(imageCarteMonumentB3);
        imageCarteMonumentB4 = new JLabel(new ImageIcon(Art.getImage("cartes/M4EP")));
        contenantJoueurB.add(imageCarteMonumentB4);

        joueurB.add(contenantJoueurB);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur C /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurC = new JPanel();

        JPanel infoJoueurC = new JPanel(new GridLayout(3, 1));

        infoJoueurC.add(new JLabel("Joueur numéro 3"));
        pieceJoueurC = new JLabel("Pièces : 3");
        infoJoueurC.add(pieceJoueurC);

        carteJoueurC = new JComboBox(nomCartes);
        infoJoueurC.add(carteJoueurC);

        contenantJoueurC.add(infoJoueurC);

        imageCarteMonumentC1 = new JLabel(new ImageIcon(Art.getImage("cartes/M1EP")));
        contenantJoueurC.add(imageCarteMonumentC1);
        imageCarteMonumentC2 = new JLabel(new ImageIcon(Art.getImage("cartes/M2EP")));
        contenantJoueurC.add(imageCarteMonumentC2);
        imageCarteMonumentC3 = new JLabel(new ImageIcon(Art.getImage("cartes/M3EP")));
        contenantJoueurC.add(imageCarteMonumentC3);
        imageCarteMonumentC4 = new JLabel(new ImageIcon(Art.getImage("cartes/M4EP")));
        contenantJoueurC.add(imageCarteMonumentC4);

        joueurC.add(contenantJoueurC);
        joueurC.setVisible(false);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur D /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurD = new JPanel();

        JPanel infoJoueurD = new JPanel(new GridLayout(3, 1));

        infoJoueurD.add(new JLabel("Joueur numéro 4"));
        pieceJoueurD = new JLabel("Pièces : 3");
        infoJoueurD.add(pieceJoueurD);

        carteJoueurD = new JComboBox(nomCartes);
        infoJoueurD.add(carteJoueurD);

        contenantJoueurD.add(infoJoueurD);

        imageCarteMonumentD1 = new JLabel(new ImageIcon(Art.getImage("cartes/M1EP")));
        contenantJoueurD.add(imageCarteMonumentD1);
        imageCarteMonumentD2 = new JLabel(new ImageIcon(Art.getImage("cartes/M2EP")));
        contenantJoueurD.add(imageCarteMonumentD2);
        imageCarteMonumentD3 = new JLabel(new ImageIcon(Art.getImage("cartes/M3EP")));
        contenantJoueurD.add(imageCarteMonumentD3);
        imageCarteMonumentD4 = new JLabel(new ImageIcon(Art.getImage("cartes/M4EP")));
        contenantJoueurD.add(imageCarteMonumentD4);

        joueurD.add(contenantJoueurD);
        joueurD.setVisible(false);
        /////////////////////////////////////////////////////////////////////////////////////


        add(panelGlobal);
        setVisible(true);
        pack();
    }

    private String[] majMainJoueur(int numeroJoueur, JComboBox comboJoueur) {

        ArrayList<Carte> tmp = metier.getJoueur(numeroJoueur-1).getMain();

        String[] main = new String[tmp.size()-4];
        int size = tmp.size();
        for(int cpt = 0; cpt < size; cpt++) {
            String nom = tmp.get(cpt).getNom();
            if(!tmp.get(cpt).getCouleur().equals("MARRON")) {
                int occu = 1;
                for (int cpt2 = cpt + 1; cpt2 < size; cpt2++) {
                    if (tmp.get(cpt2).getNom().equals(nom)) {
                        occu++;
                        tmp.remove(cpt2);
                        size--;
                    }
                }
                main[cpt] = tmp.get(cpt).getNom() + " x" + occu;
            }
        }

        DefaultComboBoxModel a = new DefaultComboBoxModel(main);
        comboJoueur.setModel(a);

        return main;
    }

    void initialiserCartes(Map<String, List<Carte>> pioche) {

        this.pioche = pioche;
        this.nomCartes = new String[pioche.size()];

        for (int cpt = 0; cpt < this.nomCartes.length; cpt++) {
            this.nomCartes[cpt] = ((List<Carte>) pioche.values().toArray()[cpt]).get(0).getNom();
            this.acheterBatimentListe.addItem(this.nomCartes[cpt]);
        }

        this.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String iden = null;
            String nom = event.getItem().toString();

            for (Map.Entry<String, List<Carte>> entry : this.pioche.entrySet())
                if (entry.getValue().size() > 0 && entry.getValue().get(0).getNom().equals(nom))
                    iden = entry.getKey();


            nombreDeCarte.setText("");

            // On affiche le nom de la carte par son String qui correspond au nom du fichier png
            if (iden != null) {
                imageCarte.setIcon(new ImageIcon(Art.getImage("cartes/" + iden)));
                // On met à jour l'affichage du nombre de cette carte
                nombreDeCarte.setText("Il reste " + String.valueOf(pioche.get(iden).size()) + " cartes "
                        + event.getItem().toString());
            }


            // Affichage des monuments

            if (event.getItem().toString().equals("Gare"))
                imageCarte.setIcon(new ImageIcon(Art.getImage("cartes/" + "M1")));
            if (event.getItem().toString().equals("Centre commercial"))
                imageCarte.setIcon(new ImageIcon(Art.getImage("cartes/" + "M2")));
            if (event.getItem().toString().equals("Parc d'attractions"))
                imageCarte.setIcon(new ImageIcon(Art.getImage("cartes/" + "M3")));
            if (event.getItem().toString().equals("Tour radio"))
                imageCarte.setIcon(new ImageIcon(Art.getImage("cartes/" + "M4")));


        }
    }

    public void setPanelJoueurs(int nombreDeJoueurs) {

        majMainJoueur(1, carteJoueurA);
        majMainJoueur(2, carteJoueurB);

        if (nombreDeJoueurs > 2){
            joueurC.setVisible(true);
            majMainJoueur(3, carteJoueurC);
        }
        if (nombreDeJoueurs > 3) {
            joueurD.setVisible(true);
            majMainJoueur(4, carteJoueurD);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==passerTourButton){

            System.out.println("passer tour");
            passerTour=true;
            construire = false;
            acheter = false;

        } else if(e.getSource()==construireMonumenButton){

            System.out.println("construire monument");
            construire=true;
            passerTour = false;
            acheter = false;

        } else if(e.getSource()==acheterBatimentButton){

            System.out.println("acheter batiment");
            acheter=true;
            construire = false;
            passerTour = false;
        }
    }

    public JLabel getImageDeUn() {
        return imageDeUn;
    }

    public JLabel getImageDeDeux() {
        return imageDeDeux;
    }

    public boolean isPasserTour() {
        return passerTour;
    }

    public boolean isConstruire() {
        return construire;
    }

    public boolean isAcheter() {
        return acheter;
    }

    public JComboBox getAcheterBatimentListe() {
        return acheterBatimentListe;
    }

    public JComboBox getConstruireMonumentListe() {
        return construireMonumentListe;
    }
}
