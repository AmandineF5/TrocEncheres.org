package fr.eni.projet01.trocenchere.bll;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.binding.StringFormatter;

import fr.eni.project01.trocenchere.dal.enchere.EnchereDAO;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class EnchereManager {
	EnchereDAO enchereDAO;
	Enchere enchere;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void ajouterEnchere (Enchere enchere) throws BusinessException {
		this.enchereDAO.insert(enchere);
		Integer points = enchere.getPoints();
		Vente vente = enchere.getConcerne();
		int noVente = vente.getNoVente();
		Enchere meilleureEnchere = this.trouverHighestBid(noVente);
		Integer pointsMeilleureEnchere = meilleureEnchere.getPoints();
		
		if (points > pointsMeilleureEnchere) {
			VenteManager vm = new VenteManager();
			vm.mettreAJourPrixVente (noVente, points);
		}
		
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
	
	public void supprimerEnchere (int noVente) throws BusinessException {
		this.enchereDAO.delete(noVente);
	}
	
	public String trouverClassementEnchere (int noVente, int noUtilisateurSession) throws BusinessException {
		String classement=null;
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = this.selectionnerEnchereByIdVente(noVente);
		int nbEnchere = listeEnchere.size();
		int noUtilisateurEnchere;
		Utilisateur utilisateurEnchere;
		int index = 0;
		
		for(Enchere e : listeEnchere) {
			utilisateurEnchere = e.getEncherit();
			noUtilisateurEnchere = utilisateurEnchere.getNoUtilisateur();
			
			if (noUtilisateurEnchere==noUtilisateurSession) {
				break;
			}
			
			index++;		
		}
		
		classement = String.format("%d / %d", index, nbEnchere);		
		return classement;
	}
	
	public Enchere trouverHighestBid (int noVente) throws BusinessException {
		Enchere highestBid=null;
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = this.selectionnerEnchereByIdVente(noVente);
		highestBid = listeEnchere.get(0);	
		return highestBid;
	}
	
	
	
	
}
