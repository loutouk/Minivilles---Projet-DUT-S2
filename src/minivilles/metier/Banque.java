public class Banque {

	private int solde;
	
	public Banque() {
		this.solde = 1000;
	}
	
	public boolean retrait(int nb) {
		if(this.solde > nb) {
			this.solde -= nb;
			return true;
		}
		return false;
	}
}
