package fr.eni.projet01.trocenchere.dal.vente;

import java.util.List;

import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public interface VenteDAO {
	
	/**
	 * création d'un article à vendre
	 * @param Vente
	 * @throws BusinessException 
	 */
	public void insert (Vente Vente) throws BusinessException;
	
	/**
	 * affichage de la vente par noVente
	 * @param noVente
	 * @return une vente
	 * @throws BusinessException 
	 */
	public Vente selectById (int noVente) throws BusinessException;
	
	/**
	 * Récupère toutes les ventes publiées par un utilisateur
	 * @param noUtilisateur
	 * @return la liste des ventes appartenant à l'utilisateur
	 * @throws BusinessException 
	 */
	public  List<Vente> selectAll (int noUtilisateur) throws BusinessException;
	
	/**
	 * Chercher et récupère toutes les ventes publiées comportant le mot clé
	 * @param keyWord
	 * @return une liste de vente
	 */
	public List<Vente> searchByKeyWord (String keyWord)throws BusinessException;
	
	/**
	 * cherche et récupère une vente par categorie
	 * @param libelle ou noCategorie
	 * @return une liste de vente
	 */
	public List<Vente> searchByCatagory (String libelle)throws BusinessException; //ou par int noCategorie
	
	/**
	 * suppression d'une vente
	 * @param noVente
	 * @throws BusinessException 
	 */
	public void delete (int noVente) throws BusinessException;
	
}
