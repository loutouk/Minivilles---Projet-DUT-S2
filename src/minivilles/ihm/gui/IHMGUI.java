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


	public IHMGUI(Controleur ctrl) {
		super(ctrl);
		this.fenetre = new Fenetre(ctrl.getMetier());
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
	public void initialiserCartes(ArrayList<Carte> pioche) {

	    this.fenetre.initialiserCartes(IHM.grouperCartes(pioche));
        fenetre.setPanelJoueurs(nombreDeJoueurs);
	}

	@Override
	public int choixMenu() {

	    return 1;
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

		Integer[] nb = {2,3,4};
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
		return 0;
	}

	@Override
	public int choixDebugDe() {
		return 0;
	}

	@Override
	public String choixAchatBatiment() {
		return "";
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

	    fenetre.getImageDeUn().setIcon(new ImageIcon(Art.getImage("des/3")));

	    if(de2!=0){
            fenetre.getImageDeDeux().setIcon(new ImageIcon(Art.getImage("des/3")));
        }

	}

	@Override
	public void afficherBilanTour(Joueur joueur, int piecesAv, int nbDes, int des, List<Carte> cartesLancees) {

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
