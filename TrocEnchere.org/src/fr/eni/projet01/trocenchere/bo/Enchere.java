package fr.eni.projet01.trocenchere.bo;

import java.time.LocalDateTime;


public class Enchere {
	private LocalDateTime dateEnchere;
	private Vente concerne;
	private Utilisateur encherit;

	/** Constructeur vide
	 * @param dateEnchere
	 */
	public Enchere() {
	}


	/**
	 * @param dateEnchere
	 */
	public Enchere(LocalDateTime dateEnchere) {
		super();
		this.dateEnchere = dateEnchere;
	}
	
	/**
	 * @param dateEnchere
	 * @param concerne
	 * @param encherit
	 */
	public Enchere(LocalDateTime dateEnchere, Vente concerne, Utilisateur encherit) {
		super();
		this.dateEnchere = dateEnchere;
		this.concerne = concerne;
		this.encherit = encherit;
	}

	/**
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public Vente getConcerne() {
		return concerne;
	}


	public void setConcerne(Vente concerne) {
		this.concerne = concerne;
	}


	public Utilisateur getEncherit() {
		return encherit;
	}


	public void setEncherit(Utilisateur encherit) {
		this.encherit = encherit;
	}


	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", concerne=" + concerne + ", encherit=" + encherit + "]";
	}
	
	
}
