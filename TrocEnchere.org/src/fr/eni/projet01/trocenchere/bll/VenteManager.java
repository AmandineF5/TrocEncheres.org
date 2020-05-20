
// AMANDINE ET LESLIE

package fr.eni.projet01.trocenchere.bll;

import java.util.ArrayList;

import java.util.List;

import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.dal.vente.VenteDAO;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * @author Amandine
 * @author modifié par Amandine, Leslie
 * @info gestion des ventes
 */

public class VenteManager {
	VenteDAO venteDAO;
	Vente vente;
	
	public VenteManager() {
		this.venteDAO = DAOFactory.getVenteDAO();
	}

	/**
	 * @author Amandine
	 * @param objet Vente
	 * @return la vente qui vient d'être créée
	 * @throws BusinessException
	 */
	public Vente ajouterVente (Vente vente) throws BusinessException {
		return venteDAO.insert(vente);
	}
	

	/**
	 * @author Amandine
	 * @param numéro vente
	 * @return la vente qui vient d'être créée
	 * @throws BusinessException
	 */
	public Vente selectionnerVenteById (int noVente) throws BusinessException {
		Vente vente;
		vente = venteDAO.selectById (noVente);
		return vente;
	}

	/**
	 * @author Amandine
	 * @param numéro d'utilisateur
	 * @return lliste de vente
	 * @throws BusinessException
	 */
	public List<Vente> selectionnerVenteUtilisateur (int noUtilisateur)  throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.selectAll (noUtilisateur);
		return listeVente;
	}
	
	/**
	 * @author Amandine
	 * @param numéro vente
	 * @return la vente qui vient d'être créée
	 * @throws BusinessException
	 */
	public Vente selectionnerVenteByIdNonPubliee (int noVente) throws BusinessException {
		Vente vente;
		vente = venteDAO.selectByIdNonPublic (noVente);
		return vente;
	}

	/**
	 * @author Amandine
	 * @param numéro d'utilisateur
	 * @return lliste de vente
	 * @throws BusinessException
	 */
	public List<Vente> selectionnerVenteUtilisateurNonPubliee (int noUtilisateur)  throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.selectAllNonPublic (noUtilisateur);
		return listeVente;
	}
	
	/**
	 * @author Amandine
	 * @param numéro de vente
	 * @return vide
	 * @throws BusinessException
	 */
	public void supprimerVente (int noVente) throws BusinessException {
		venteDAO.delete(noVente);
	}
	
	
	public void supprimerVenteUser (int noVendeur) throws BusinessException {
		venteDAO.deleteUser(noVendeur);
	}
	
	
	/**
	 * @author Leslie
	 * selectionnerVenteByCategory / searchByCatagory
	 * @param libelle
	 * @return une liste de ventes publiées classées par catégorie
	 * @throws BusinessException
	 */
	public List<Vente> selectionnerVenteByCategory(int noCategorie) throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.searchByCatagory(noCategorie);
		return listeVente;	
	}
	/**
	 * @author Leslie
	 * @param keyWord
	 * @return une liste de ventes publiées classées par mot clé
	 * @throws BusinessException
	 */
	public List<Vente> selectionnerVenteByKeyWord(String keyWord) throws BusinessException{
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.searchByKeyWord(keyWord);
		return listeVente;
	}
	
	
	public List<Categorie> selectionnerCategorie() throws BusinessException{
		List<Categorie> listeCategorie = new ArrayList<>();
		listeCategorie = venteDAO.selectCatagory();
		return listeCategorie;
	}
	
	/**
	 * @author Amandine
	 * @param numéro de vente et dernière enchère récente (points)
	 * @return vide
	 * @throws BusinessException
	 */
	public void mettreAJourPrixVente (int noVente, Integer points) throws BusinessException {
		venteDAO.update(noVente, points);
	}
	
	public List<Vente> selectionnerToutesVentes() throws BusinessException{
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.selectAll();
		return listeVente;
	}
}

	

