
// AMANDINE ET JANET

package fr.eni.projet01.trocenchere.bll;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.binding.StringFormatter;

import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.dal.enchere.EnchereDAO;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class EnchereManager {
	EnchereDAO enchereDAO;
	Enchere enchere;

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void ajouterEnchere(Enchere enchere) throws BusinessException {
		int noUser = enchere.getEncherit().getNoUtilisateur();
		int noSale = enchere.getConcerne().getNoVente();
		Enchere test = selectEnchereByUserIdEtNoVente(noUser, noSale);
		if (test != null) {
			enchereDAO.updateEnchere(enchere);
		} else {
			this.enchereDAO.insert(enchere);
			Integer points = enchere.getPoints();
			Vente vente = enchere.getConcerne();
			int noVente = vente.getNoVente();
			Enchere meilleureEnchere = this.trouverHighestBid(noVente);
			Integer pointsMeilleureEnchere = meilleureEnchere.getPoints();

			if (points > pointsMeilleureEnchere) {
				VenteManager vm = new VenteManager();
				vm.mettreAJourPrixVente(noVente, points);
			}
		}

	}
	
	public void deleteUserbid(int noVente, int noUtilisateur) throws BusinessException {
		enchereDAO.deleteOne(noVente, noUtilisateur);
	}

	// varification que il n'y a pas deja un enchere par l'utilisateur pour ce
	// produit
	public Enchere selectEnchereByUserIdEtNoVente(int noUtilisateur, int noVente) throws BusinessException {

		return enchereDAO.selectOneByUserIdVenteId(noUtilisateur, noVente);
	}

	public List<Enchere> selectionnerEnchereByIdUser(int noUtilisateur) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = enchereDAO.selectByUserId(noUtilisateur);
		return listeEnchere;
	}

	// Don't understand this function? why not call the selectbyventeId directly
	public List<Enchere> selectionnerEnchereByIdVente(int noVente) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = enchereDAO.selectByVenteId(noVente);
		return listeEnchere;
	}

	public void supprimerEnchere(int noVente) throws BusinessException {
		this.enchereDAO.delete(noVente);
	}

	public String trouverClassementEnchere(int noVente, int noUtilisateurSession) throws BusinessException {
		String classement = null;
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = this.selectionnerEnchereByIdVente(noVente);
		int nbEnchere = listeEnchere.size();
		int noUtilisateurEnchere;
		Utilisateur utilisateurEnchere;
		int index = 0;

		for (Enchere e : listeEnchere) {
			utilisateurEnchere = e.getEncherit();
			noUtilisateurEnchere = utilisateurEnchere.getNoUtilisateur();

			if (noUtilisateurEnchere == noUtilisateurSession) {
				break;
			}

			index++;
		}

		classement = String.format("%d / %d", index, nbEnchere);
		return classement;
	}

	public Enchere trouverHighestBid(int noVente) throws BusinessException {
		Enchere highestBid = null;
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = enchereDAO.selectByVenteId(noVente);
		highestBid = listeEnchere.get(0);
		return highestBid;
	}
	
	//reimbourse the non-winners their credit at the end of the sale
	public void RembourserEnFinVente(int noVente)throws BusinessException {
		//get list of enchere linked to the vente
		List<Enchere> e = enchereDAO.selectByVenteId(noVente);
		//remove highest bid
		e.remove(0);
		//recredit the remaining encheres
		for(Enchere a : e) {
			//user concerned
			Utilisateur aCredite = a.getEncherit();
			//montant d'enchere enchereCredit + userCredit
			int newCredit = a.getPoints() + aCredite.getCredit();
			//set new credit
			aCredite.setCredit(newCredit);
			//update user in the database
			UtilisateurManager uM = new UtilisateurManager();
			uM.mettreAJourUtilisateur(aCredite);
		}
		
	}

}
