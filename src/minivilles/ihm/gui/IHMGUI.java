package minivilles.ihm.gui;

import minivilles.Controleur;
import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class IHMGUI extends IHM {

	private Fenetre fenetre;
	private int nombreDeJoueurs;

	private String menu;


	public IHMGUI(Controleur ctrl) {
		super(ctrl);
		this.fenetre = new Fenetre(ctrl.getMetier());
	}

	@Override
	public void afficherMenuPrincipal() {
		this.menu = "principal";
	}

	@Override
	public void afficherMenuAchat(Joueur joueur) {
		this.menu = "achat";
	}

	@Override
	public void afficherMenuRejouer() {
		this.menu = "rejouer";
	}

	@Override
	public void initialiserCartes(ArrayList<Carte> pioche) {

		this.fenetre.initialiserCartes(IHM.grouperCartes(pioche));
		fenetre.setPanelJoueurs(nombreDeJoueurs);
	}

	@Override
	public int choixMenu() {
		return this.choixMenu(1, Integer.MAX_VALUE);
	}

	@Override
	public int choixMenu(int min, int max) {
		if (this.menu.equals("achat")) {
			// On attends le clic sur un des boutons d'action de l'IHM
			while (!this.fenetre.isAcheter() && !this.fenetre.isConstruire() && !this.fenetre.isPasserTour()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			return this.fenetre.isAcheter() ? 1 : ((this.fenetre.isConstruire()) ? 2 : 3);
		}

		return 1;
	}

	@Override
	public String choixStringMenu() {
		return null;
	}

	@Override
	public int choixNbJoueurs() {

		Integer[] nb = {2, 3, 4};
		nombreDeJoueurs = (Integer) JOptionPane.showInputDialog(fenetre,
				"",
				"Nombre de joueurs",
				JOptionPane.QUESTION_MESSAGE,
				null,
				nb,
				nb[0]);

		return nombreDeJoueurs;
	}

	@Override
	public int choixNbDes() {

		// Dialog box qui choisi un ou deux de si la gare
		return 1;
	}

	@Override
	public int choixDebugDe() {
		return 1;
	}

	@Override
	public String choixAchatBatiment() {
        return fenetre.getAcheterBatimentListe().toString();
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
	public int getDe() {
		return 0;
	}

	@Override
	public void afficherPlateau() {

	}

	@Override
	public void afficherDebutTour(Joueur j) {

	}

	@Override
	public void afficherLigneCarte(ArrayList<Carte> listeCartes) {

	}

	@Override
	public void afficherColonneCarte(ArrayList<Carte> listeCartes) {

	}

	@Override
	public void afficherValeurDes(int de1, int de2) {

		fenetre.getImageDeUn().setIcon(new ImageIcon(Art.getImage("des/" + de1)));

		if (de2 != 0) {
			fenetre.getImageDeDeux().setIcon(new ImageIcon(Art.getImage("des/" + de2)));
			this.fenetre.getImageDeDeux().setVisible(true);
		} else
			this.fenetre.getImageDeDeux().setVisible(false);
	}

	@Override
	public void afficherBilanTour(Joueur joueur, int piecesAv, int nbDes, int de1, int de2, List<Carte> cartesLancees) {
		this.afficherValeurDes(de1, de2);
	}

	@Override
	public void afficherGagnant(Joueur gagnant) {

	}

	@Override
	public void afficherModeEvaluation() {
		System.out.println("Mode évaluation activé !");
	}

	@Override
	public void afficherErreur(String erreur) {
		JOptionPane.showMessageDialog(this.fenetre, erreur, "Erreur !", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void nettoyerAffichage() {

	}
}
