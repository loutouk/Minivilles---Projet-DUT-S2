package minivilles.ihm.gui;

import minivilles.Controleur;
import minivilles.ihm.IHM;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;

import java.util.ArrayList;
import java.util.List;

public class IHMGUI extends IHM {


	public IHMGUI(Controleur ctrl) {
		super(ctrl);
	}


	@Override
	public void afficherMenuPrincipal() {

	}

	@Override
	public void afficherMenuAchat(Joueur joueur) {

	}

	@Override
	public void afficherMenuRejouer() {

	}

	@Override
	public int choixMenu() {
		return 0;
	}

	@Override
	public int choixMenu(int min, int max) {
		return 0;
	}

	@Override
	public String choixStringMenu() {
		return null;
	}

	@Override
	public int choixNbJoueurs() {
		return 0;
	}

	@Override
	public int choixNbDes() {
		return 0;
	}

	@Override
	public int choixDebugDe() {
		return 0;
	}

	@Override
	public String choixIdentifiantCarte() {
		return null;
	}

	@Override
	public String choixCarteCentreAffaire(String joueur) {
		return null;
	}

	@Override
	public String choixJoueurCentreAffaire() {
		return null;
	}

	@Override
	public String choixJoueurChaineTV() {
		return null;
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
	public void afficherValeurDes(int de) {

	}

	@Override
	public void afficherBilanTour(Joueur joueur, int piecesAv, int nbDes, int des, List<Carte> cartesLancees) {

	}

	@Override
	public void afficherGagnant(Joueur gagnant) {

	}

	@Override
	public void afficherModeEvaluation() {

	}

	@Override
	public void nettoyerAffichage() {

	}
}
