package fr.eni.projet01.trocenchere.dal;

import java.util.List;

import fr.eni.projet01.trocenchere.bo.Vente;

public interface VenteDAO {
	/**
	 * création d'un article à vendre
	 * @param Vente
	 */
	public void insert (Vente Vente);
	
	/**
	 * affichage de la vente par noVente
	 * @param noVente
	 * @return une vente
	 */
	public Vente selectById (int noVente);
	
	/**
	 * Récupère toutes les ventes publiées par un utilisateur
	 * @param noUtilisateur
	 * @return la liste des ventes appartenant à l'utilisateur
	 */
	public  List<Vente> selectAll (int noUtilisateur);
	
	/**
	 * Récupère toutes les ventes publiées comportant le mot clé
	 * @param keyWord
	 * @return une liste de vente
	 */
	public List<Vente> searchByKeyWord (String keyWord);
	
	
	
	
	
}
