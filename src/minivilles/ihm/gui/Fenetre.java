package minivilles.ihm.gui;

import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;
import minivilles.metier.cartes.monuments.Monument;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

/**
 * Fenetre.java
 * JFrame de la classe IHMGUI
 * @see minivilles.ihm.gui.IHMGUI
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public class Fenetre extends JFrame implements ItemListener, ActionListener, ComponentListener, WindowFocusListener {

    // Image affichée à côté de la réserve
    private JLabel imageCarte;
    // Nombre de carte de l'image précédente
    private JLabel nombreDeCarte;

    private JPanel partieBasse;
    private JComboBox<String> acheterBatimentListe;
    private JComboBox<String> construireMonumentListe;
    private JButton acheterBatimentButton;
    private JButton construireMonumenButton;

    private JPanel[] joueurPanels;

    private JButton passerTourButton;

	private JLabel tourDuJoueur;

    private JLabel imageDeUn;
    private JLabel imageDeDeux;

    private JDialog dialog;

    private String[] nomCartes = new String[0];

	private Map<String, List<Carte>> pioche;

    private boolean passerTour;
    private boolean construire;
    private boolean acheter;


    Fenetre() {

        this.passerTour = false;
        this.acheter = false;
        this.construire = false;

        setTitle("Miniville");
        setDefaultCloseOperation(3);

        JPanel panelGlobal = new JPanel();
        BoxLayout globalLayout = new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS);
        panelGlobal.setLayout(globalLayout);
        panelGlobal.setBorder(new EmptyBorder(0, 0, 0, 0));
        JPanel partieHaute = new JPanel(new FlowLayout());
        JPanel gauche = new JPanel();
        BoxLayout gaucheLayout = new BoxLayout(gauche, BoxLayout.X_AXIS);
        gauche.setLayout(gaucheLayout);
        JPanel droite = new JPanel();
        this.partieBasse = new JPanel(new GridLayout(2, 2));

        partieHaute.add(gauche);
        partieHaute.add(droite);
        panelGlobal.add(partieHaute);
        panelGlobal.add(partieBasse);

        // Gauche ///////////////////////////////////////////////////////////////////////////
        JPanel contenantGauche = new JPanel(new BorderLayout());
        nombreDeCarte = new JLabel("");
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

        acheterBatimentListe = new JComboBox<>(nomCartes);
        acheterBatimentListe.addItemListener(this);
        boutons.add(acheterBatimentListe);
        acheterBatimentButton = new JButton("Acheter ce bâtiment");
        acheterBatimentButton.addActionListener(this);
        boutons.add(acheterBatimentButton);

		String[] nomMonuments = {"Gare", "Centre commercial", "Parc d'attractions", "Tour radio"};
		construireMonumentListe = new JComboBox<>(nomMonuments);
        construireMonumentListe.addItemListener(this);
        boutons.add(construireMonumentListe);
        construireMonumenButton = new JButton("Construire ce monument");
        construireMonumenButton.addActionListener(this);
        boutons.add(construireMonumenButton);

        passerTourButton = new JButton("Passer");
        passerTourButton.addActionListener(this);

        tourDuJoueur = new JLabel("Partie non commencée");

        boutons.add(tourDuJoueur);
        boutons.add(passerTourButton);

        JPanel imageDe = new JPanel(new GridLayout(1, 2, 50, 50));
        imageDeUn = new JLabel(new ImageIcon(Art.getImage("des/1")));
        imageDeUn.setBorder(new EmptyBorder(50, 50, 50, 50));
		imageDeUn.setVisible(false);
        imageDeDeux = new JLabel(new ImageIcon(Art.getImage("des/1")));
        imageDeDeux.setBorder(new EmptyBorder(45, 45, 45, 45));
		imageDeDeux.setVisible(false);
        imageDe.add(imageDeUn);
        imageDe.add(imageDeDeux);


        contenantDroit.add(boutons);
        contenantDroit.add(imageDe);
        droite.add(contenantDroit);
        /////////////////////////////////////////////////////////////////////////////////////

        add(panelGlobal);

        addComponentListener(this);
        addWindowFocusListener(this);

        setVisible(false);
    }


	JLabel getImageDeUn() {
		return imageDeUn;
	}

	JLabel getImageDeDeux() {
		return imageDeDeux;
	}

	JLabel getTourLabel() {
		return this.tourDuJoueur;
	}

	boolean isPasserTour() {
		return passerTour;
	}

	boolean isConstruire() {
		return construire;
	}

	boolean isAcheter() {
		return acheter;
	}

	void resetMenu() {
		this.acheter = false;
		this.construire = false;
		this.passerTour = false;
	}

	JComboBox getAcheterBatimentListe() {
		return acheterBatimentListe;
	}

	JComboBox getConstruireMonumentListe() {
		return construireMonumentListe;
	}


	void openDialog(String message, String imageIcon) {
		this.dialog = new JDialog();
		ImageIcon loader = new ImageIcon(Art.class.getResource(imageIcon));
		JLabel label = new JLabel(message, loader, JLabel.CENTER);

		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.getContentPane().setBackground(Color.WHITE);
		dialog.setTitle(message);
		dialog.setAlwaysOnTop(true);
		dialog.setUndecorated(true);
		dialog.setResizable(false);
		dialog.add(label);
		dialog.pack();
		dialog.setLocationRelativeTo(this);

		dialog.setVisible(true);
	}

	void removeDialog() {
    	if (this.dialog == null) return;

    	this.dialog.setVisible(false);
    	this.dialog.dispose();

    	this.dialog = null;
	}


    void majInfoJoueurs(List<Joueur> listeJoueur) {
		for (Joueur aListeJoueur : listeJoueur)
			this.majInfoJoueur(aListeJoueur);
    }

	void majInfoJoueur(Joueur joueur) {
		JPanel panelJ = this.joueurPanels[joueur.getNum() - 1];
		if (panelJ == null) return;

		JPanel contenantP = (JPanel) panelJ.getComponent(0);
		JPanel infoP = (JPanel) contenantP.getComponent(0);

		// Mise à jour du nombre de pièces
		((JLabel) infoP.getComponent(1)).setText("Pièces : " + joueur.getPieces());

		// Mise à jour de sa liste de cartes
		JComboBox<String> liste = (JComboBox) infoP.getComponent(2);
		Map<String, List<Carte>> cartesJ = IHM.grouperCartes(joueur.getMain());

		liste.removeAllItems();

		for (Map.Entry<String, List<Carte>> entree : cartesJ.entrySet()) {
			int nb = entree.getValue().size();
			if (nb == 0) continue;
			Carte c = entree.getValue().get(0);
			if (c instanceof Monument) continue;

			liste.addItem(c.getNom() + "  x" + nb);
		}

		// Mise à jour des monuments du joueur
		for (int cpt = 0; cpt < 4; cpt++) {
			JLabel labelMonument = (JLabel) contenantP.getComponent(cpt + 1);
			Monument monument = (Monument) cartesJ.get("M" + (cpt + 1)).get(0);

			String labelEc = (monument.estEnConstruction()) ? "EP" : "P";

			labelMonument.setIcon(new ImageIcon(Art.getImage("cartes/M" + (cpt + 1) + labelEc)));
		}
	}

    void majPioche(Map<String, List<Carte>> pioche) {
        this.pioche = pioche;
        this.nomCartes = new String[pioche.size()];

        this.acheterBatimentListe.removeAllItems();

        for (int cpt = 0; cpt < this.nomCartes.length; cpt++) {
            this.nomCartes[cpt] = ((List<Carte>) pioche.values().toArray()[cpt]).get(0).getNom();
            this.acheterBatimentListe.addItem(this.nomCartes[cpt]);
        }

        this.setVisible(true);
    }

	void majPanelsJoueurs(int nbJoueurs) {
    	// On supprime d'abord les anciens panels
		if (this.joueurPanels != null)
			for (JPanel panel : this.joueurPanels)
				if (panel != null) {
					panel.setVisible(false);
					panel.getParent().remove(panel);
				}


    	this.joueurPanels = new JPanel[nbJoueurs];


		for (int cpt = 0; cpt < nbJoueurs; cpt++) {
			JPanel panelJo = new JPanel();
			JPanel contenantJoueur = new JPanel();

			// On colore la case du premier joueur
			if (cpt == 0) panelJo.setBackground(Color.ORANGE);

			JPanel infoJoueur = new JPanel(new GridLayout(3, 1));
			JLabel piecesJoueur = new JLabel("Pièces : -");
			JComboBox<String> carteJoueur = new JComboBox<>();

			infoJoueur.add(new JLabel("Joueur " + (cpt + 1)));
			infoJoueur.add(piecesJoueur);

			contenantJoueur.setOpaque(false);
			infoJoueur.setOpaque(false);

			infoJoueur.add(carteJoueur);
			contenantJoueur.add(infoJoueur);

			contenantJoueur.add(new JLabel(new ImageIcon(Art.getImage("cartes/M1EP"))));
			contenantJoueur.add(new JLabel(new ImageIcon(Art.getImage("cartes/M2EP"))));
			contenantJoueur.add(new JLabel(new ImageIcon(Art.getImage("cartes/M3EP"))));
			contenantJoueur.add(new JLabel(new ImageIcon(Art.getImage("cartes/M4EP"))));

			panelJo.add(contenantJoueur);
			partieBasse.add(panelJo);
			this.joueurPanels[cpt] = panelJo;
		}

		setVisible(true);
		pack();
	}



    void nouveauJoueurCourant(Joueur joueur) {
    	if (this.joueurPanels == null) return;

    	for (int cpt = 1; cpt <= this.joueurPanels.length; cpt++)
    		this.joueurPanels[cpt - 1].setBackground((joueur.getNum() == cpt) ? Color.ORANGE : null);
	}

	void activerBoutons(boolean me) {
    	this.acheterBatimentButton.setEnabled(me);
    	this.construireMonumenButton.setEnabled(me);
    	this.passerTourButton.setEnabled(me);
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


	@Override
	public void componentResized(ComponentEvent componentEvent) {
	}

	@Override
	public void componentMoved(ComponentEvent componentEvent) {
		if (this.dialog != null) this.dialog.setLocationRelativeTo(this);
	}

	@Override
	public void componentShown(ComponentEvent componentEvent) {
	}

	@Override
	public void componentHidden(ComponentEvent componentEvent) {
	}

	@Override
	public void windowGainedFocus(WindowEvent windowEvent) {
		if (this.dialog != null) this.dialog.setAlwaysOnTop(true);
	}

	@Override
	public void windowLostFocus(WindowEvent windowEvent) {
		if (this.dialog != null) this.dialog.setAlwaysOnTop(false);
	}
}
