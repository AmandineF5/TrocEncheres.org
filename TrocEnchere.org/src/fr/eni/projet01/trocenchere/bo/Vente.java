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



	public Vente() {
		super();
	}
	
	
	
	
	
}
