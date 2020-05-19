
// JANET

package fr.eni.projet01.trocenchere.bo;

import java.time.LocalDate;

/**
 * 
 * @author jaja
 *
 */
public class Vente {
	private int noVente;
	private String nomArticle;
	private String description;
	private LocalDate dateFinEncheres;  //changé de LocalDateTime pour LocalDate
	private Integer miseAPrix;
	private Integer prixVente;
	private Categorie categorieArticle;
	//private Utilisateur acheteur;
	private Utilisateur vendeur;
	private Retrait lieuRetrait;
	private Boolean publie;
	private String nomImage;

	// contstructors
	public Vente(int noVente, String nomArticle, String description, LocalDate dateFinEncheres, Integer miseAPrix,
			Integer prixVente, Categorie categorieArticle, Utilisateur vendeur,
			Retrait lieuRetrait, Boolean publie, String nomImage) {
		super();
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorieArticle = categorieArticle;

		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;
		this.publie = publie;
		this.nomImage = nomImage;
	}

	public Vente(int noVente, String nomArticle, String description, LocalDate dateFinEncheres, Integer miseAPrix,
			Categorie categorieArticle, Utilisateur vendeur) {
		this(noVente, nomArticle, description, dateFinEncheres, miseAPrix, 0, categorieArticle, vendeur, null, false, null);
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

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
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

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
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
				+ ", categorieArticle=" + categorieArticle + ", vendeur=" + vendeur
				+ ", lieuRetrait=" + lieuRetrait.toString() + ", publie=" + publie + ", nomImage=" + nomImage + "]";
	}

}
