package fr.eni.projet01.trocenchere.bo;

import java.time.LocalDateTime;

public class Vente {
	private int noVente;
	private int nomArticle;
	private String description;
	private LocalDateTime dateFinEncheres;
	private float miseAPrix;
	private float prixVente;
	private Categorie categorieArticle;
	private Utilisateur achete;
	private Utilisateur vend;
	private Retrait lieuRetrait;
	
	
	//contstructors
	public Vente(int noVente, int nomArticle, String description, LocalDateTime dateFinEncheres, float miseAPrix,
			float prixVente, Categorie categorieArticle, Utilisateur achete, Utilisateur vend, Retrait lieuRetrait) {
		super();
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorieArticle = categorieArticle;
		this.achete = achete;
		this.vend = vend;
		this.lieuRetrait = lieuRetrait;
	}
	
	public Vente(int noVente, int nomArticle, String description, LocalDateTime dateFinEncheres, float miseAPrix,
			Categorie categorieArticle, Utilisateur vend) {
		super();
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorieArticle = categorieArticle;
		this.vend = vend;
	}
	
	//getters and setters
	public Vente() {
		super();
	}

	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public int getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(int nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public float getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(float miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public float getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

	public Categorie getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public Utilisateur getAchete() {
		return achete;
	}

	public void setAchete(Utilisateur achete) {
		this.achete = achete;
	}

	public Utilisateur getVend() {
		return vend;
	}

	public void setVend(Utilisateur vend) {
		this.vend = vend;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	@Override
	public String toString() {
		return "Vente [noVente=" + noVente + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente
				+ ", categorieArticle=" + categorieArticle + ", achete=" + achete + ", vend=" + vend + ", lieuRetrait="
				+ lieuRetrait + "]";
	}
	

		
	
	
}
