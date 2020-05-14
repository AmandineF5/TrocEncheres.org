package fr.eni.projet01.trocenchere.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Enchere {
	private LocalDate dateEnchere;
	private Vente concerne;
	private Utilisateur encherit;
	private Integer points;

	/** Constructeur vide
	 * @param dateEnchere
	 */
	public Enchere() {
	}

	
	/**
	 * @param dateEnchere
	 * @param concerne
	 * @param encherit
	 * @param points
	 */
	public Enchere(LocalDate dateEnchere, Vente concerne, Utilisateur encherit, Integer points) {
		super();
		this.dateEnchere = dateEnchere;
		this.concerne = concerne;
		this.encherit = encherit;
		this.points = points;
	}


	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", concerne=" + concerne + ", encherit=" + encherit + ", points="
				+ points + "]";
	}

	
}
