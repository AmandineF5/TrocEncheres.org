
// CREE PAR AMANDINE ET CORENTIN, RETOUCHE PAR TOUT LE MONDE

package fr.eni.projet01.trocenchere.dal.vente;

import java.util.List;

import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public interface VenteDAO {

	/**
	 * création d'un article à vendre
	 * @param Vente
	 * @return 
	 * @throws BusinessException 
	 */
	public Vente insert (Vente Vente) throws BusinessException;

	/**
	 * affichage de la vente par noVente
	 * @param noVente
	 * @return une vente
	 * @throws BusinessException 
	 */

	public void update (int noVente, Integer points) throws BusinessException;


	public Vente selectById (int noVente) throws BusinessException;

	/**
	 * Récupère toutes les ventes publiées par un utilisateur
	 * @param noUtilisateur
	 * @return la liste des ventes appartenant à l'utilisateur
	 * @throws BusinessException 
	 */
	public  List<Vente> selectAll (int noUtilisateur) throws BusinessException;
	
	/**
	 * Récupère toutes les ventes publiées
	 * @return la liste des ventes
	 * @throws BusinessException 
	 */
	public  List<Vente> selectAll () throws BusinessException;
	
	/**
	 * Chercher et récupère toutes les ventes non publiées comportant le mot clé
	 * @param keyWord
	 * @return une liste de vente
	 */

	public Vente selectByIdNonPublic (int noVente) throws BusinessException;

	/**
	 * Récupère toutes les ventes non publiées par un utilisateur
	 * @param noUtilisateur
	 * @return la liste des ventes appartenant à l'utilisateur
	 * @throws BusinessException 
	 */
	public  List<Vente> selectAllNonPublic (int noUtilisateur) throws BusinessException;

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
	public List<Vente> searchByCatagory (int noCategorie)throws BusinessException; //ou par int noCategorie

	/**
	 * suppression d'une vente
	 * @param noVente
	 * @throws BusinessException 
	 */
	public void delete (int noVente) throws BusinessException;

	/**
	 * Chercher et récupère toutes les catégories d'articles présente sur le site
	 * @return une liste de Categorie
	 * @throws BusinessException
	 */
	public List<Categorie> selectCatagory()throws BusinessException;
}
