package fr.eni.projet01.trocenchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.dal.vente.VenteDAO;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class VenteManager {
	VenteDAO venteDAO;
	Vente vente;
	
	public VenteManager() {
		this.venteDAO = DAOFactory.getVenteDAO();
	}

	public Vente ajouterVente (Vente vente) throws BusinessException {
		return venteDAO.insert(vente);
	}
	

	public Vente selectionnerVenteById (int noVente) throws BusinessException {
		Vente vente;
		vente = venteDAO.selectById (noVente);
		return vente;
	}

	public List<Vente> selectionnerVenteUtilisateur (int noUtilisateur)  throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.selectAll (noUtilisateur);
		return listeVente;
	}
	
	public void supprimerVente (int noVente) throws BusinessException {
		venteDAO.delete(noVente);
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
	
	public void mettreAJourPrixVente (int noVente, Integer points) throws BusinessException {
		venteDAO.update(noVente, points);
	}
}

	

