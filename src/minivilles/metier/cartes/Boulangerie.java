package minivilles.metier.cartes;

public class Boulangerie extends Carte {

	public Boulangerie() {
		super(
				"2-3", "Boulangerie",
				"Vert", 2, 3, 1
		);
	}

	@Override
	public boolean lancerEffet() {
		return false;
	}
}
