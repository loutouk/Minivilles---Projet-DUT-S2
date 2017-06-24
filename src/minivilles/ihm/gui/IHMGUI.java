package minivilles.ihm.gui;

import minivilles.Controleur;
import minivilles.ihm.IHM;
import minivilles.metier.Banque;
import minivilles.metier.Joueur;
import minivilles.metier.cartes.Carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IHMGUI extends IHM {

	private Fenetre fenetre;

	private boolean attenteMenu;
	private JDialog loaderNetDialog;


	public IHMGUI(Controleur ctrl) {
		super(ctrl);
		this.fenetre = new Fenetre();
	}


	@Override
	public void initialiserPlateau(ArrayList<Carte> pioche, int nbJoueurs) {
		this.fenetre.majPioche(IHM.grouperCartes(pioche));
		this.fenetre.majPanelsJoueurs(nbJoueurs);

		this.fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.fenetre.setVisible(true);
		this.fenetre.pack();
	}

	@Override
	public void majPlateau(List<Joueur> joueurs) {
		this.fenetre.majInfoJoueurs(joueurs);
	}

	public boolean choixEstServeur() {
		int dialogResult = JOptionPane.showConfirmDialog (
				null, "Mode réseau actif.\nVoulez-vous créer une partie ?", "Minivilles : connexion à une partie", JOptionPane.YES_NO_OPTION
		);

		return dialogResult == JOptionPane.YES_OPTION;
	}

	public String choixServeurHote() {
		Object result;

		do {
			result = JOptionPane.showInputDialog(this.fenetre, "Adresse de connexion de la partie :");
		}
		while (result == null);

		return result.toString();
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
				null, "Vous avez la tour Radio.\nVoulez-vous relancer vos dés ?", "Effet de la tour Radio", JOptionPane.YES_NO_OPTION
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
	public String choixCarteCentreAffaire(String labelJoueur, List<Carte> cartesEchangeables) {
		List<String> nomsCartes = new ArrayList<>();

		for (Carte carte : cartesEchangeables)
			nomsCartes.add(carte.getNom());

		Object selection = JOptionPane.showInputDialog(null, "Choisissez la carte à échanger avec le joueur " + labelJoueur + ":\nOu cliquez sur annuler.",
				"Effet de la carte Centre d'affaires", JOptionPane.QUESTION_MESSAGE, null, nomsCartes.toArray(), null);

		return (selection != null) ? selection.toString() : "-1";
	}

	@Override
	public String choixJoueurCentreAffaire(List<Joueur> joueurs, Joueur joueurCourant) {
		List<String> nomsJoueurs = new ArrayList<>();

		for (Joueur joueur : joueurs)
			if (!joueur.equals(joueurCourant))
				nomsJoueurs.add("Joueur " + joueur.getNum());

		Object selection = JOptionPane.showInputDialog(null, "Choisissez le joueur avec qui échanger une carte :\nOu cliquez sur annuler.",
				"Effet de la carte Centre d'affaires", JOptionPane.QUESTION_MESSAGE, null, nomsJoueurs.toArray(), null);

		return (selection != null) ? selection.toString().replace("Joueur ", "") : "-1";
	}

	@Override
	public String choixJoueurChaineTV(List<Joueur> joueurs, Joueur joueurCourant) {
		List<String> nomsJoueurs = new ArrayList<>();

		for (Joueur joueur : joueurs)
			if (!joueur.equals(joueurCourant))
				nomsJoueurs.add("Joueur " + joueur.getNum());

		Object selection = JOptionPane.showInputDialog(null, "Choisissez le joueur à qui voler 5 pièces :\nOu cliquez sur annuler.",
				"Effet de la carte Chaîne de télévision", JOptionPane.QUESTION_MESSAGE, null, nomsJoueurs.toArray(), null);

		return (selection != null) ? selection.toString().replace("Joueur ", "") : "-1";
	}

	@Override
	public void afficherPlateau(List<Carte> pioche, Banque banque, List<Joueur> listeJoueur) {
		this.fenetre.majPioche(IHM.grouperCartes((ArrayList<Carte>) pioche));
		this.fenetre.majInfoJoueurs(listeJoueur);
	}

	@Override
	public void afficherAttenteReseau(String message) {
		JDialog dialog = new JDialog();
		ImageIcon loader = new ImageIcon(Art.class.getResource("/res/images/loader.gif"));
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
		dialog.setLocationRelativeTo(null);

		this.fenetre.setVisible(false);
		dialog.setVisible(true);

		this.loaderNetDialog = dialog;
	}

	@Override
	public void finAttenteReseau() {
		if (this.loaderNetDialog == null) return;

		this.loaderNetDialog.setVisible(false);
		this.loaderNetDialog.dispose();
		this.fenetre.setVisible(true);

		this.loaderNetDialog = null;
	}

	@Override
	public void nouveauTour(Joueur j, boolean vous) {
		this.attenteMenu = false;

		this.fenetre.getTourLabel().setText("Tour: joueur " + j.getNum());
		this.fenetre.nouveauJoueurCourant(j);
		this.fenetre.activerBoutons(vous);
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
	public void afficherRejouerEffet() {
		this.boiteDeDialogue("Effet du monument Parc d'attractions", "Vous avez obtenu un double !\nVous pouvez jouer 2x.", JOptionPane.INFORMATION_MESSAGE);
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
				null, "Voulez-vous charger la dernière partie sauvegardée ?", "Minivilles : chargement d'une partie", JOptionPane.YES_NO_OPTION
		);

		return dialogResult == JOptionPane.YES_OPTION;
	}
}
