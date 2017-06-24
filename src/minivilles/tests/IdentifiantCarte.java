package minivilles.tests;

import minivilles.metier.cartes.*;

/**
 * IdentifiantCarte.java
 * Utilisé pour l'évaluation
 * @author Richard Blondel
 * @author Valentin Dulong
 * @author Maxime Malgorn
 * @author Louis Boursier
 * @version 1.0
 */

public enum IdentifiantCarte {

	CHAMPS_DE_BLE("1", ChampsDeBle.class),
	FERME("2", Ferme.class),
	BOULANGERIE("2-3", Boulangerie.class),
	CAFE("3", Cafe.class),
	SUPERETTE("4", Superette.class),
	FORET("5", Foret.class),
	STADE("6:1", Stade.class),
	CHAINE_DE_TELEVISION("6:2", ChaineDeTelevision.class),
	CENTRE_AFFAIRES("6:3", CentreAffaires.class),
	FROMAGERIE("7", Fromagerie.class),
	FABRIQUE_MEUBLE("8", FabriqueMeuble.class),
	MINE("9", Mine.class),
	RESTAURANT("9-10", Restaurant.class),
	VERGER("10", Verger.class),
	MARCHE_DE_FRUITS_ET_LEGUMES("11-12", MarcheDeFruitsEtLegumes.class);


	private String identifiant;
	private Class<? extends Carte> classe;

	IdentifiantCarte(String identifiant, Class<? extends Carte> classe) {
		this.identifiant = identifiant;
		this.classe = classe;
	}


	public static Carte nouvelleCarte(String identifiant) {
		Class<? extends Carte> classe = IdentifiantCarte.getIdentifiantClasse(identifiant);

		if (classe != null) {
			try {
				return classe.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static Class<? extends Carte> getIdentifiantClasse(String identifiant) {
		for (IdentifiantCarte idCarte : IdentifiantCarte.values())
			if (idCarte.identifiant.equals(identifiant))
				return idCarte.classe;

		return null;
	}

}
