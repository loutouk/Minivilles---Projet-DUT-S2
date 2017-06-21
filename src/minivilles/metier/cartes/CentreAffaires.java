package minivilles.metier.cartes;
import minivilles.metier.*;
public class CentreAffaires extends Carte {

	public CentreAffaires () {
		super("6:3", "Centre d'affaires", "Violet", 6, 7);
	}

	@Override
	public void lancerEffet(Metier metier) {
		int choixJoueur             = 0;
		String idCarteEchange       = "";
		String idCarteJoueurCourant = "";
		Joueur joueurCible          = null;
		Joueur joueurCourant        = metier.getJoueurCourant();
		Carte cCourant              = null;
		Carte cCible                = null;
		boolean carteValideCourant  = false;
		boolean carteValideCible    = false;
		int nbJoueur					 = metier.getListeJoueur().size();
		
		while(choixJoueur < 1 || choixJoueur > nbJoueur) {
			choixJoueur = Integer.parseInt(metier.getIhm().choixJoueurCentreAffaire());
		}
		
		joueurCible = metier.getListeJoueur().get(choixJoueur - 1);
		
		while (!carteValideCible) {
			idCarteEchange = metier.getIhm().choixCarteCentreAffaire();
			for (Carte c : joueurCible.getMain()) {
				if(c.getIdentifiant().equals(idCarteEchange) && !c.getCouleur().equals("VIOLET")) {
					carteValideCible = true;
					cCible = c;
				}
			}
		}
		
		while (!carteValideCourant) {
			idCarteJoueurCourant = metier.getIhm().choixCarteCentreAffaire();
			for (Carte c : joueurCourant.getMain()) {
				if(c.getIdentifiant().equals(idCarteEchange) && !c.getCouleur().equals("VIOLET")) {
					carteValideCourant = true;
					cCourant = c;
				}
			}
		}
		
		joueurCible.echangerCarte(cCible,joueurCourant);
		joueurCourant.echangerCarte(cCourant, joueurCible);

	}


}
