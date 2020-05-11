package fr.eni.projet01.trocenchere.bo;

import java.time.LocalDateTime;


public class Enchere {
	private LocalDateTime dateEnchere;

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
	
	
}
