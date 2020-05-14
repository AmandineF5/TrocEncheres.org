package fr.eni.projet01.trocenchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
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

	public void ajouterVente (Vente vente) throws BusinessException {
		venteDAO.insert(vente);
	}
	

	public List<Vente> selectionnerVenteById (int noVente) throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.selectById (noVente);
		return listeVente;
	}

	public List<Vente> selectionnerVenteUtilisateur (int noUtilisateur)  throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		listeVente = venteDAO.selectAll (noUtilisateur);
		return listeVente;
	}
	
	public void supprimerVente (int noVente) throws BusinessException {
		venteDAO.delete(noVente);
	}
	
}

	

