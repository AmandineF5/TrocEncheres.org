package fr.eni.projet01.trocenchere.bo;

public class Retrait {
	private String rue;
	private String codePostal;
	private String ville;
	
	/**
	 * Constructeur avec tous les éléments
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */	
	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	/**
	 * Constructeur vide
	 */	
	public Retrait() {
	}
	
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	
	
	
}
