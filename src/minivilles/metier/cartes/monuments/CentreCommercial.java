package minivilles.metier.cartes.monuments;

/**
 * Classe <i>CentreCommercial</i>, qui hérite de {@link minivilles.metier.cartes.monuments.Monument}.
 * <p>
 * Cette classe définit la carte <i>CentreCommercial</i> ainsi que son effet.
 *
 * @see minivilles.metier.cartes.Carte
 * @see minivilles.metier.cartes.monuments.Monument
 */
public class CentreCommercial extends Monument {

	public CentreCommercial() {
		super("M2", "Centre commercial", -1, 10);
	}

}
