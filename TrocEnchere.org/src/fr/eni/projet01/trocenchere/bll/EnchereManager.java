package fr.eni.projet01.trocenchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.project01.trocenchere.dal.enchere.EnchereDAO;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class EnchereManager {
	EnchereDAO enchereDAO;
	Enchere enchere;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void ajouterEnchere (Enchere enchere) throws BusinessException {
		enchereDAO.insert(enchere);
	}
	

	public  List<Enchere> selectionnerEnchereByIdUser (int noUtilisateur) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = enchereDAO.selectByUserId(noUtilisateur);
		return listeEnchere;
	}

	public  List<Enchere> selectionnerEnchereByIdVente (int noVente) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = enchereDAO.selectByUserId(noVente);
		return listeEnchere;
	}
	
	public void supprimerVente (int noVente) throws BusinessException {
		enchereDAO.delete(noVente);
	}
	
}
