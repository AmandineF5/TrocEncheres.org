package fr.eni.projet01.trocenchere.bo;

import java.time.LocalDateTime;

/**
 * 
 * @author coren
 *
 */
public class Vente {
	private int noVente;
	private String nomArticle;
	private String description;
	private LocalDateTime dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private Categorie categorieArticle;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private Retrait lieuRetrait;
	private Boolean publie;
	private String nomImage;

	// contstructors
	public Vente(int noVente, String nomArticle, String description, LocalDateTime dateFinEncheres, Integer miseAPrix,
			Integer prixVente, Categorie categorieArticle, Utilisateur acheteur, Utilisateur vendeur,
			Retrait lieuRetrait, Boolean publie, String nomImage) {
		super();
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorieArticle = categorieArticle;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;
		this.publie = publie;
		this.nomImage = nomImage;
	}

	public Vente(int noVente, String nomArticle, String description, LocalDateTime dateFinEncheres, Integer miseAPrix,
			Categorie categorieArticle, Utilisateur vendeur) {
		this(noVente, nomArticle, description, dateFinEncheres, miseAPrix, 0, categorieArticle, null, vendeur, null, false, null);
	}

	// getters and setters
	public Vente() {
		super();
	}

	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
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

	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public float getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public Categorie getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur achete) {
		this.acheteur = acheteur;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vend) {
		this.vendeur = vendeur;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public Boolean getPublie() {
		return publie;
	}

	public void setPublie(Boolean publie) {
		this.publie = publie;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
	
	@Override
	public String toString() {
		return "Vente [noVente=" + noVente + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente
				+ ", categorieArticle=" + categorieArticle + ", acheteur=" + acheteur + ", vendeur=" + vendeur
				+ ", lieuRetrait=" + lieuRetrait + ", publie=" + publie + ", nomImage=" + nomImage + "]";
	}

}
