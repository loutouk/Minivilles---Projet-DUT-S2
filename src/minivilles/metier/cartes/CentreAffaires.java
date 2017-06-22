package minivilles.metier.cartes;

import minivilles.Controleur;
import minivilles.metier.Joueur;
import minivilles.metier.Metier;

public class CentreAffaires extends Carte {

	public CentreAffaires () {
		super("6:3", "Centre d'affaires", "Violet", 6, 7);
	}


	@Override
	public void lancerEffet(Metier metier) {
		int choixJoueur             = 0;

		String idCarteEchange;
		String idCarteJoueurCourant;
		Joueur joueurCible;

		Joueur joueurCourant        = this.getJoueur();
		Carte cCourant              = null;
		Carte cCible                = null;
		boolean carteValideCourant  = false;
		boolean carteValideCible    = false;
		int nbJoueur				= metier.getListeJoueur().size();
		
		while(choixJoueur < 1 || choixJoueur > nbJoueur) {
			try{
				choixJoueur = Integer.parseInt(Controleur.getIhm().choixJoueurCentreAffaire());
			} catch (NumberFormatException e) {
				// saisie invalide
			}
		}

		if (choixJoueur != -1){
			joueurCible = metier.getListeJoueur().get(choixJoueur - 1);

			while (!carteValideCible) {
				idCarteEchange = Controleur.getIhm().choixCarteCentreAffaire("cible");
				for (Carte c : joueurCible.getMain()) {
					if(c.getIdentifiant().equals(idCarteEchange) && !c.getCouleur().equals("VIOLET")) {
						carteValideCible = true;
						cCible = c;
					}
				}
			}

			while (!carteValideCourant) {
				idCarteJoueurCourant = Controleur.getIhm().choixCarteCentreAffaire("courant");
				for (Carte c : joueurCourant.getMain()) {
					if(c.getIdentifiant().equals(idCarteJoueurCourant) && !c.getCouleur().equals("VIOLET")) {
						carteValideCourant = true;
						cCourant = c;
					}
				}
			}

			joueurCourant.echangerCarte(cCourant, cCible, joueurCible);
		}

	}

}
