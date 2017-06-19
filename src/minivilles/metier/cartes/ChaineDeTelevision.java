package minivilles.metier.cartes;

public class ChaineDeTelevision extends Carte {

	public ChaineDeTelevision() {
		super(
				"6:2", "Chaîne de télévision",
				"Spécial", 6, 7
		);
	}

	@Override
	public boolean lancerEffet() {
		return false;
	}
}
