package minivilles.ihm.gui;

import minivilles.Controleur;
import minivilles.ihm.IHM;
import minivilles.metier.Banque;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class IHMGUI extends IHM {

	private Fenetre fenetre;
	private int nombreDeJoueurs;

	private boolean attenteMenu;


	public IHMGUI(Controleur ctrl) {
		super(ctrl);
		this.fenetre = new Fenetre();
	}


	@Override
	public void initialiserPlateau(ArrayList<Carte> pioche, int nbJoueurs) {
		this.fenetre.majPioche(IHM.grouperCartes(pioche));
		this.fenetre.initialiserPanelsJoueurs(nbJoueurs);

		this.nombreDeJoueurs = nbJoueurs;


		this.fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.fenetre.setVisible(true);
		this.fenetre.pack();
	}

	@Override
	public int choixNbJoueurs() {

		Integer[] nb = {2, 3, 4};
		Integer nbJ = (Integer) JOptionPane.showInputDialog(this.fenetre,
				"",
				"Minivilles : nombre de joueurs",
				JOptionPane.QUESTION_MESSAGE,
				null,
				nb,
				nb[0]);

		if (nbJ == null) System.exit(0);
		return nbJ;
	}

	@Override
	public int choixMenuPrincipal() {
		// Dans l'interface graphique, on lance forcément le jeu.
		return 1;
	}

	@Override
	public int choixNbDes() {
		Integer[] nb = {1, 2};

		Integer choix = (Integer) JOptionPane.showInputDialog(this.fenetre,
				"Combien de dés voulez-vous lancer ?",
				"Nombre de dés à lancer",
				JOptionPane.QUESTION_MESSAGE,
				null,
				nb,
				nb[0]);

		if (choix == null) return nb[0];

		return choix;
	}

	@Override
	public int choixDebugDe() {
		return 1;
	}

	@Override
	public int choixAchatMenu(Joueur joueur) {
		System.out.println("menu de sélection");
		this.attenteClicMenu();
		return this.fenetre.isAcheter() ? 1 : ((this.fenetre.isConstruire()) ? 2 : 3);
	}

	@Override
	public int choixRejouerTour() {
		int dialogResult = JOptionPane.showConfirmDialog (
				null, "Vous avez la tour Radio.\nVoulez-vous rejouer ?", "Effet de la tour Radio", JOptionPane.YES_NO_OPTION
		);

		return (dialogResult == JOptionPane.YES_OPTION) ? 1 : 2;
	}

	@Override
	public String choixAchatBatiment() {
		if (this.attenteMenu) {
			this.attenteMenu = false;
			return "-1";
		}

		this.attenteMenu = true;

		return fenetre.getAcheterBatimentListe().getSelectedItem().toString();
	}

	@Override
	public String choixAchatMonument() {
		if (this.attenteMenu) {
			this.attenteMenu = false;
			return "-1";
		}

		this.attenteMenu = true;

		return fenetre.getConstruireMonumentListe().getSelectedItem().toString();
	}

	@Override
	public String choixCarteCentreAffaire(String joueur) {
		return "";
	}

	@Override
	public String choixJoueurCentreAffaire() {
		return "";
	}

	@Override
	public String choixJoueurChaineTV() {
		return "";
	}

	@Override
	public void afficherPlateau(List<Carte> pioche, Banque banque, List<Joueur> listeJoueur) {
		this.fenetre.majPioche(IHM.grouperCartes((ArrayList<Carte>) pioche));
		this.fenetre.majInfoJoueurs(listeJoueur);
	}

	@Override
	public void nouveauTour(Joueur j) {
		this.attenteMenu = false;

		this.fenetre.getTourLabel().setText("Tour: joueur " + j.getNum());
		this.fenetre.nouveauJoueurCourant(j);
	}

	@Override
	public void afficherLigneCarte(ArrayList<Carte> listeCartes) {
	}

	@Override
	public void afficherColonneCarte(ArrayList<Carte> listeCartes) {
	}

	@Override
	public void afficherDes(int de1, int de2) {

		fenetre.getImageDeUn().setVisible(true);
		fenetre.getImageDeUn().setIcon(new ImageIcon(Art.getImage("des/" + de1)));

		if (de2 != 0) {
			fenetre.getImageDeDeux().setIcon(new ImageIcon(Art.getImage("des/" + de2)));
			this.fenetre.getImageDeDeux().setVisible(true);
		} else
			this.fenetre.getImageDeDeux().setVisible(false);
	}

	@Override
	public void afficherBilanTour(Joueur joueur, int piecesAv, int nbDes, int de1, int de2, List<Carte> cartesLancees) {
		this.afficherDes(de1, de2);
		this.fenetre.majInfoJoueur(joueur);
	}

	@Override
	public void afficherGagnant(Joueur gagnant) {
		this.boiteDeDialogue(
				"Partie terminée !",
				"Le joueur " + gagnant.getNum() + " est le grand gagnant !\nBravo à lui !",
				JOptionPane.INFORMATION_MESSAGE
		);

		System.exit(0);
	}

	@Override
	public void afficherModeEvaluation() {
		this.boiteDeDialogue(
				"Minivilles : mode évaluation",
				"Mode évaluation activé !\nCe dernier n'est pas représentatif d'une partie classique.",
				JOptionPane.WARNING_MESSAGE
		);
	}

	@Override
	public void afficherErreur(String erreur) {
		this.boiteDeDialogue("Erreur !", erreur, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void nettoyerAffichage() {
		// Inutile de nettoyer l'affichage en mode graphique.
	}


	private void attenteClicMenu() {
		this.fenetre.resetMenu();

		// On attends le clic sur un des boutons d'action de l'IHM
		while (!this.fenetre.isAcheter() && !this.fenetre.isConstruire() && !this.fenetre.isPasserTour()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void boiteDeDialogue(String titre, String message, int typeMessage) {
		JOptionPane.showMessageDialog(this.fenetre, message, titre, typeMessage);
	}


	@Override
	public boolean choixChargerPartie() {
		int dialogResult = JOptionPane.showConfirmDialog (
				null, "Voulez-vous charger une partie existante ?", "Minivilles : chargement d'une partie", JOptionPane.YES_NO_OPTION
		);

		return dialogResult == JOptionPane.YES_OPTION;
	}
}
