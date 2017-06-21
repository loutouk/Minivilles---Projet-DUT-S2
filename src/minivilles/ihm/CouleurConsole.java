package minivilles.ihm;

public enum CouleurConsole {

	RESET("\u001B[0m"),
	NOIR("\u001B[30;1m"),
	ROUGE("\u001B[31;1m"),
	VERT("\u001B[32;1m"),
	JAUNE("\u001B[33;1m"),
	BLEU("\u001B[34;1m"),
	VIOLET("\u001B[35;1m"),
	CYAN("\u001B[36;1m"),
	BLANC("\u001B[37;1m");


	private String code;

	CouleurConsole(String code) {
		this.code = code;
	}

	public void print() {
		System.out.print(this.code);
	}

	public String toString() {
		return this.code;
	}

}
