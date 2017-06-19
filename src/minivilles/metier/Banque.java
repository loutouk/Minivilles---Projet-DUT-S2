package minivilles.metier;

/**
 * Classe Banque, caractérisée par un solde.
 * Les joueurs, lorsqu'ils sont amenés à le faire, peuvent piocher dans ce solde,
 * qui est initialisé par défaut à 1000 pièces.
 */
public class Banque {

	private int solde;

	public Banque() {
		this.solde = 1000;
	}

	/**
	 * Effectue un retrait dans le solde de la banque, du montant indiqué en paramètre.
	 *
	 * @param nb le montant de pièces à retirer de la banque
	 * @return un booléen, indiquand la réussite de l'opération.
	 */
	public boolean retrait(int nb) {
		if (this.solde > nb) {
			this.solde -= nb;
			return true;
		}

		return false;
	}
}
