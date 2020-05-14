package fr.eni.projet01.trocenchere.dal;

import java.util.List;

import fr.eni.projet01.trocenchere.bo.Vente;

public interface VenteDAO {
	/**
	 * création d'un article à vendre
	 * @param Vente
	 */
	public void insert (Vente Vente);
	//INSERT INTO ventes(nomarticle, description, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?)
	//INSERT INTO retraits(no_vente, rue, code_postal, ville) VALUES (?,?,?,?)
		
	/**
	 * affichage de la vente par noVente
	 * @param noVente
	 * @return une vente
	 */
	public Vente selectById (int noVente);
	//SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE no_vente = ?
		
	/**
	 * Récupère toutes les ventes publiées par un utilisateur
	 * @param noUtilisateur
	 * @return la liste des ventes appartenant à l'utilisateur
	 */
	public  List<Vente> selectAll (int noUtilisateur);
	//SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE no_utilisateur = ?
	
	/**
	 * Chercher et récupère toutes les ventes publiées comportant le mot clé
	 * @param keyWord
	 * @return une liste de vente
	 */
	public List<Vente> searchByKeyWord (String keyWord);
	//SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE nomarticle LIKE ? OR description LIKE ?
		
	/**
	 * cherche et récupère une vente par categorie
	 * @param libelle ou noCategorie
	 * @return une liste de vente
	 */
	public List<Vente> searchByCatagory (String libelle); //ou par int noCategorie
	//SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE no_categorie/libelle=?
	
	/**
	 * suppression d'une vente
	 * @param noVente
	 */
	public void delete (int noVente);
	//DELETE FROM ventes WHERE no_vente = ?
	//DELETE FROM retraits WHERE no_vente=?
	
}
