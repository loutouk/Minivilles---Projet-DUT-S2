package minivilles.metier.cartes;

import minivilles.metier.Metier;

public class MarcheDeFruitsEtLegumes extends Carte {

	public MarcheDeFruitsEtLegumes() {
		super(
				"11-12", "Marché de fruits et légumes",
				"Vert", 11, 12, 2
		);
	}

	@Override
	public void lancerEffet(Metier metier) {

	}
}
