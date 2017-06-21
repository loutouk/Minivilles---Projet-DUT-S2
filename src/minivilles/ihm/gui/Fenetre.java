package minivilles.ihm.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by louis on 21/06/17.
 */
public class Fenetre extends JFrame {

    // Noms des cartes dans la réserve
    private JComboBox listePioche;
    // Image affichée à côté de la réserve
    private JLabel imageCarte;
    // Nombre de carte de l'image précédente
    private JLabel nombreDeCarte;
    private JComboBox acheterBatimentListe;
    private JComboBox construireMonumentListe;
    private JButton acheterBatimentButton;
    private JButton construireMonumenButton;

    private JButton passerTour;
    private JLabel infoCommandes;

    private JLabel pieceJoueurA;
    private JLabel pieceJoueurB;
    private JLabel pieceJoueurC;
    private JLabel pieceJoueurD;

    private JComboBox carteJoueurA;
    private JComboBox carteJoueurB;
    private JComboBox carteJoueurC;
    private JComboBox carteJoueurD;

    private JComboBox monumentJoueurA;
    private JComboBox monumentJoueurB;
    private JComboBox monumentJoueurC;
    private JComboBox monumentJoueurD;

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

    private String[] nomCartes = {"Champs de blé", "Ferme", "Boulangerie", "Café",
            "Supérette", "Forêt", "Stade", "Chaîne de télévision", "Centre d'affaires",
            "Fromagerie", "Fabrique de meubles", "Mines", "Restaurant", "Verger",
            "Marché de fruits et légumes"};
    private String[] nomMonuments = {"Gare", "Centre commercial", "Parc d'attractions", "Tour radio"};

    public Fenetre() {

        setTitle("Miniville");
        // Plein écran
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(3);

        JPanel panelGlobal = new JPanel();
        BoxLayout globalLayout = new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS);
        panelGlobal.setLayout(globalLayout);
        panelGlobal.setBorder(new EmptyBorder( 0, 0 ,0,0 ));
        JPanel partieHaute = new JPanel(new FlowLayout());
        JPanel gauche = new JPanel();
        //gauche.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout gaucheLayout = new BoxLayout(gauche, BoxLayout.X_AXIS);
        gauche.setLayout(gaucheLayout);
        JPanel droite = new JPanel();
        //droite.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel partieBasse = new JPanel(new GridLayout(2,2));
        JPanel joueurA = new JPanel();
        //joueurA.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel joueurB = new JPanel();
        //joueurB.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel joueurC = new JPanel();
        //joueurC.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel joueurD = new JPanel();
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
        listePioche = new JComboBox(nomCartes);
        listePioche.setBorder(new EmptyBorder(5,5,5,5));
        nombreDeCarte = new JLabel("Nombre de cette carte dans la réserve : 6");
        nombreDeCarte.setBorder(new EmptyBorder(5,5,5,5));
        imageCarte = new JLabel(new ImageIcon(Art.getImage("img1")));
        contenantGauche.add(listePioche, BorderLayout.NORTH);
        contenantGauche.add(imageCarte, BorderLayout.CENTER);
        contenantGauche.add(nombreDeCarte, BorderLayout.SOUTH);
        gauche.add(contenantGauche);
        /////////////////////////////////////////////////////////////////////////////////////



        // Droite ///////////////////////////////////////////////////////////////////////////
        JPanel contenantDroit = new JPanel();
        BoxLayout contenantDroitLayout = new BoxLayout(contenantDroit, BoxLayout.Y_AXIS);
        contenantDroit.setLayout(contenantDroitLayout);

        JPanel boutons = new JPanel(new GridLayout(3,2,14,14));

        acheterBatimentListe = new JComboBox(nomCartes);
        boutons.add(acheterBatimentListe);
        acheterBatimentButton = new JButton("Acheter ce bâtiment");
        boutons.add(acheterBatimentButton);

        construireMonumentListe = new JComboBox(nomMonuments);
        boutons.add(construireMonumentListe);
        construireMonumenButton = new JButton("Construire ce monument");
        boutons.add(construireMonumenButton);

        passerTour = new JButton("Passer");

        infoCommandes = new JLabel("Informations sur les commandes");

        tourDuJoueur = new JLabel("Tour du joueur numéro ");

        boutons.add(tourDuJoueur);
        boutons.add(passerTour);

        JPanel imageDe = new JPanel(new GridLayout(1,2,50,50));
        imageDeUn = new JLabel(new ImageIcon(Art.getImage("un")));
        imageDeUn.setBorder(new EmptyBorder(50,50,50,50));
        imageDeDeux = new JLabel(new ImageIcon(Art.getImage("un")));
        imageDeDeux.setBorder(new EmptyBorder(45,45,45,45));
        imageDe.add(imageDeUn);
        imageDe.add(imageDeDeux);


        contenantDroit.add(boutons);
        contenantDroit.add(imageDe);
        droite.add(contenantDroit);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur A /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurA = new JPanel();

        JPanel infoJoueurA = new JPanel(new GridLayout(4,1));

        infoJoueurA.add(new JLabel("Joueur numéro 1"));
        pieceJoueurA = new JLabel("Pièces : 3");
        infoJoueurA.add(pieceJoueurA);

        carteJoueurA = new JComboBox(nomCartes);
        infoJoueurA.add(carteJoueurA);

        monumentJoueurA = new JComboBox(nomMonuments);
        infoJoueurA.add(monumentJoueurA);

        contenantJoueurA.add(infoJoueurA);

        imageCarteMonumentA1 = new JLabel(new ImageIcon(Art.getImage("mF1small")));
        contenantJoueurA.add(imageCarteMonumentA1);
        imageCarteMonumentA2 = new JLabel(new ImageIcon(Art.getImage("mF2small")));
        contenantJoueurA.add(imageCarteMonumentA2);
        imageCarteMonumentA3 = new JLabel(new ImageIcon(Art.getImage("mF3small")));
        contenantJoueurA.add(imageCarteMonumentA3);
        imageCarteMonumentA4 = new JLabel(new ImageIcon(Art.getImage("mF4small")));
        contenantJoueurA.add(imageCarteMonumentA4);

        joueurA.add(contenantJoueurA);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur B /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurB = new JPanel();

        JPanel infoJoueurB = new JPanel(new GridLayout(4,1));

        infoJoueurB.add(new JLabel("Joueur numéro 1"));
        pieceJoueurB = new JLabel("Pièces : 2");
        infoJoueurB.add(pieceJoueurB);

        carteJoueurB = new JComboBox(nomCartes);
        infoJoueurB.add(carteJoueurB);

        monumentJoueurB = new JComboBox(nomMonuments);
        infoJoueurB.add(monumentJoueurB);

        contenantJoueurB.add(infoJoueurB);

        imageCarteMonumentB1 = new JLabel(new ImageIcon(Art.getImage("mF1small")));
        contenantJoueurB.add(imageCarteMonumentB1);
        imageCarteMonumentB2 = new JLabel(new ImageIcon(Art.getImage("mF2small")));
        contenantJoueurB.add(imageCarteMonumentB2);
        imageCarteMonumentB3 = new JLabel(new ImageIcon(Art.getImage("mF3small")));
        contenantJoueurB.add(imageCarteMonumentB3);
        imageCarteMonumentB4 = new JLabel(new ImageIcon(Art.getImage("mF4small")));
        contenantJoueurB.add(imageCarteMonumentB4);

        joueurB.add(contenantJoueurB);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur C /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurC = new JPanel();

        JPanel infoJoueurC = new JPanel(new GridLayout(4,1));

        infoJoueurC.add(new JLabel("Joueur numéro 3"));
        pieceJoueurC = new JLabel("Pièces : 3");
        infoJoueurC.add(pieceJoueurC);

        carteJoueurC = new JComboBox(nomCartes);
        infoJoueurC.add(carteJoueurC);

        monumentJoueurC = new JComboBox(nomMonuments);
        infoJoueurC.add(monumentJoueurC);

        contenantJoueurC.add(infoJoueurC);

        imageCarteMonumentC1 = new JLabel(new ImageIcon(Art.getImage("mF1small")));
        contenantJoueurC.add(imageCarteMonumentC1);
        imageCarteMonumentC2 = new JLabel(new ImageIcon(Art.getImage("mF2small")));
        contenantJoueurC.add(imageCarteMonumentC2);
        imageCarteMonumentC3 = new JLabel(new ImageIcon(Art.getImage("mF3small")));
        contenantJoueurC.add(imageCarteMonumentC3);
        imageCarteMonumentC4 = new JLabel(new ImageIcon(Art.getImage("mF4small")));
        contenantJoueurC.add(imageCarteMonumentC4);

        joueurC.add(contenantJoueurC);
        /////////////////////////////////////////////////////////////////////////////////////

        // Joueur D /////////////////////////////////////////////////////////////////////////
        JPanel contenantJoueurD = new JPanel();

        JPanel infoJoueurD = new JPanel(new GridLayout(4,1));

        infoJoueurD.add(new JLabel("Joueur numéro 4"));
        pieceJoueurD = new JLabel("Pièces : 3");
        infoJoueurD.add(pieceJoueurD);

        carteJoueurD = new JComboBox(nomCartes);
        infoJoueurD.add(carteJoueurD);

        monumentJoueurD = new JComboBox(nomMonuments);
        infoJoueurD.add(monumentJoueurD);

        contenantJoueurD.add(infoJoueurD);

        imageCarteMonumentD1 = new JLabel(new ImageIcon(Art.getImage("mF1small")));
        contenantJoueurD.add(imageCarteMonumentD1);
        imageCarteMonumentD2 = new JLabel(new ImageIcon(Art.getImage("mF2small")));
        contenantJoueurD.add(imageCarteMonumentD2);
        imageCarteMonumentD3 = new JLabel(new ImageIcon(Art.getImage("mF3small")));
        contenantJoueurD.add(imageCarteMonumentD3);
        imageCarteMonumentD4 = new JLabel(new ImageIcon(Art.getImage("mF4small")));
        contenantJoueurD.add(imageCarteMonumentD4);

        joueurD.add(contenantJoueurD);
        /////////////////////////////////////////////////////////////////////////////////////


        add(panelGlobal);
        setVisible(true);
        pack();
    }
}
